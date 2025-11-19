package service.impl;


import config.ApplicationConfig;
import config.Constants;
import dto.MatriculaDTO;
import dto.MatriculaRequestDTO;
import exception.*;
import external.client.DiscenteClient;
import external.client.DisciplinaClient;
import mapper.MatriculaMapper;
import model.Discente;
import model.Disciplina;
import model.Matricula;
import repository.MatriculaRepository;
import service.MatriculaService;
import util.IdGenerator;
import util.Logger;
import validator.MatriculaValidator;
import validator.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação CRÍTICA do serviço de Matrícula com TODAS as regras de negócio.
 *
 * Regras aplicadas:
 * 1. Máximo 5 disciplinas simultâneas
 * 2. Situação acadêmica ATIVO
 * 3. Disciplina do mesmo curso
 * 4. Verificação de vagas
 *
 * Padrões aplicados:
 * - GRASP: Controller, High Cohesion, Low Coupling
 * - SOLID: SRP, DIP, OCP
 */
public class MatriculaServiceImpl implements MatriculaService {

    private static final Logger logger = Logger.getInstance();
    private final MatriculaRepository matriculaRepository;
    private final DiscenteClient discenteClient;
    private final DisciplinaClient disciplinaClient;
    private final MatriculaMapper mapper;
    private final MatriculaValidator validator;
    private final ApplicationConfig config;

    public MatriculaServiceImpl(MatriculaRepository matriculaRepository,
                                DiscenteClient discenteClient,
                                DisciplinaClient disciplinaClient) {
        this.matriculaRepository = matriculaRepository;
        this.discenteClient = discenteClient;
        this.disciplinaClient = disciplinaClient;
        this.mapper = new MatriculaMapper();
        this.validator = new MatriculaValidator();
        this.config = ApplicationConfig.getInstance();
    }

    @Override
    public MatriculaDTO realizarMatricula(MatriculaRequestDTO request) {
        logger.info("Iniciando processo de matrícula...");

        // 1. Validação de entrada
        ValidationResult validationResult = validator.validate(request);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorMessage());
        }

        try {
            // 2. Buscar discente
            Discente discente = discenteClient.buscarPorMatricula(request.getMatriculaDiscente())
                    .orElseThrow(() -> new DiscenteNotFoundException(request.getMatriculaDiscente()));

            // 3. Buscar disciplina
            Disciplina disciplina = disciplinaClient.buscarPorId(request.getDisciplinaId())
                    .orElseThrow(() -> new DisciplinaNotFoundException(request.getDisciplinaId()));

            // 4. REGRA: Verificar situação acadêmica
            if (!discente.isAptoParaMatricula()) {
                throw new BusinessRuleException(Constants.MSG_SITUACAO_TRANCADA);
            }

            // 5. REGRA: Verificar limite de disciplinas (máximo 5)
            long quantidadeMatriculas = matriculaRepository.countByMatriculaDiscente(
                    request.getMatriculaDiscente());

            if (quantidadeMatriculas >= config.getMaxDisciplinasPorDiscente()) {
                throw new BusinessRuleException(Constants.MSG_LIMITE_DISCIPLINAS);
            }

            // 6. REGRA: Verificar se disciplina é do mesmo curso
            if (!discente.getCurso().equalsIgnoreCase(disciplina.getCurso())) {
                throw new BusinessRuleException(Constants.MSG_CURSO_DIFERENTE);
            }

            // 7. REGRA: Verificar vagas disponíveis
            if (!disciplina.possuiVagasDisponiveis()) {
                throw new BusinessRuleException(Constants.MSG_SEM_VAGAS);
            }

            // 8. Verificar se já está matriculado
            if (matriculaRepository.existsByMatriculaDiscenteAndDisciplinaId(
                    request.getMatriculaDiscente(), request.getDisciplinaId())) {
                throw new MatriculaException("Discente já está matriculado nesta disciplina");
            }

            // 9. Criar matrícula
            Matricula matricula = new Matricula();
            matricula.setId(IdGenerator.generateMatriculaId());
            matricula.setMatriculaDiscente(request.getMatriculaDiscente());
            matricula.setDisciplinaId(request.getDisciplinaId());

            // 10. Ocupar vaga (simulação em memória)
            disciplina.ocuparVaga();

            // 11. Persistir matrícula
            matricula = matriculaRepository.save(matricula);

            logger.info("✅ Matrícula realizada com sucesso! ID: " + matricula.getId());

            MatriculaDTO dto = mapper.toDTO(matricula);
            dto.setDisciplinaNome(disciplina.getNome());

            return dto;

        } catch (DiscenteNotFoundException | DisciplinaNotFoundException |
                 BusinessRuleException | MatriculaException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao realizar matrícula: " + e.getMessage());
            throw new MatriculaException("Erro ao processar matrícula: " + e.getMessage());
        }
    }

    @Override
    public void cancelarMatricula(Long matriculaId) {
        logger.info("Cancelando matrícula ID: " + matriculaId);

        Matricula matricula = matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new MatriculaException("Matrícula não encontrada"));

        if (!matricula.isAtiva()) {
            throw new MatriculaException("Matrícula já está cancelada");
        }

        try {
            // Buscar disciplina para liberar vaga
            Disciplina disciplina = disciplinaClient.buscarPorId(matricula.getDisciplinaId())
                    .orElse(null);

            if (disciplina != null) {
                disciplina.liberarVaga();
            }

            matricula.cancelar();
            matriculaRepository.save(matricula);

            logger.info("✅ Matrícula cancelada com sucesso!");

        } catch (Exception e) {
            logger.error("Erro ao cancelar matrícula: " + e.getMessage());
            throw new MatriculaException("Erro ao cancelar matrícula: " + e.getMessage());
        }
    }

    @Override
    public List<MatriculaDTO> listarPorDiscente(String matriculaDiscente) {
        logger.info("Listando matrículas do discente: " + matriculaDiscente);

        List<Matricula> matriculas = matriculaRepository.findByMatriculaDiscente(matriculaDiscente);

        return matriculas.stream()
                .map(m -> {
                    MatriculaDTO dto = mapper.toDTO(m);
                    try {
                        Disciplina disc = disciplinaClient.buscarPorId(m.getDisciplinaId()).orElse(null);
                        if (disc != null) {
                            dto.setDisciplinaNome(disc.getNome());
                        }
                    } catch (Exception e) {
                        logger.warn("Erro ao buscar disciplina: " + e.getMessage());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<MatriculaDTO> listarTodas() {
        logger.info("Listando todas as matrículas...");

        List<Matricula> matriculas = matriculaRepository.findAll();

        return matriculas.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
