package service.impl;


import config.Constants;
import dto.ReservaDTO;
import dto.ReservaRequestDTO;
import exception.*;
import external.client.BibliotecaClient;
import external.client.DiscenteClient;
import mapper.ReservaMapper;
import model.Discente;
import model.Livro;
import model.Reserva;
import repository.ReservaRepository;
import service.ReservaService;
import util.IdGenerator;
import util.Logger;
import validator.ReservaValidator;
import validator.ValidationResult;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação CRÍTICA do serviço de Reserva com regras de negócio.
 *
 * Regras aplicadas:
 * 1. Livro deve estar DISPONÍVEL
 * 2. Discente deve existir e estar ATIVO
 *
 * Padrões aplicados:
 * - GRASP: Controller, High Cohesion
 * - SOLID: SRP, DIP
 */
public class ReservaServiceImpl implements ReservaService {

    private static final Logger logger = Logger.getInstance();
    private final ReservaRepository reservaRepository;
    private final DiscenteClient discenteClient;
    private final BibliotecaClient bibliotecaClient;
    private final ReservaMapper mapper;
    private final ReservaValidator validator;

    public ReservaServiceImpl(ReservaRepository reservaRepository,
                              DiscenteClient discenteClient,
                              BibliotecaClient bibliotecaClient) {
        this.reservaRepository = reservaRepository;
        this.discenteClient = discenteClient;
        this.bibliotecaClient = bibliotecaClient;
        this.mapper = new ReservaMapper();
        this.validator = new ReservaValidator();
    }

    @Override
    public ReservaDTO realizarReserva(ReservaRequestDTO request) {
        logger.info("Iniciando processo de reserva...");

        // 1. Validação
        ValidationResult validationResult = validator.validate(request);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrorMessage());
        }

        try {
            // 2. Buscar discente
            Discente discente = discenteClient.buscarPorMatricula(request.getMatriculaDiscente())
                    .orElseThrow(() -> new DiscenteNotFoundException(request.getMatriculaDiscente()));

            // 3. Verificar situação acadêmica
            if (!discente.isAptoParaMatricula()) {
                throw new BusinessRuleException("Discente com situação acadêmica trancada não pode reservar livros");
            }

            // 4. Buscar livro
            Livro livro = bibliotecaClient.buscarPorId(request.getLivroId())
                    .orElseThrow(() -> new LivroNotFoundException(request.getLivroId()));

            // 5. REGRA: Verificar disponibilidade
            if (!livro.isDisponivel()) {
                throw new BusinessRuleException(Constants.MSG_LIVRO_INDISPONIVEL);
            }

            // 6. Verificar se já tem reserva ativa
            if (reservaRepository.existsByMatriculaDiscenteAndLivroId(
                    request.getMatriculaDiscente(), request.getLivroId())) {
                throw new ReservaException("Discente já possui reserva ativa para este livro");
            }

            // 7. Criar reserva
            Reserva reserva = new Reserva();
            reserva.setId(IdGenerator.generateReservaId());
            reserva.setMatriculaDiscente(request.getMatriculaDiscente());
            reserva.setLivroId(request.getLivroId());

            // 8. Marcar livro como indisponível (simulação)
            livro.reservar();

            // 9. Persistir reserva
            reserva = reservaRepository.save(reserva);

            logger.info("✅ Reserva realizada com sucesso! ID: " + reserva.getId());

            ReservaDTO dto = mapper.toDTO(reserva);
            dto.setLivroTitulo(livro.getTitulo());

            return dto;

        } catch (DiscenteNotFoundException | LivroNotFoundException |
                 BusinessRuleException | ReservaException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao realizar reserva: " + e.getMessage());
            throw new ReservaException("Erro ao processar reserva: " + e.getMessage());
        }
    }

    @Override
    public void cancelarReserva(Long reservaId) {
        logger.info("Cancelando reserva ID: " + reservaId);

        Reserva reserva = reservaRepository.findById(reservaId)
                .orElseThrow(() -> new ReservaException("Reserva não encontrada"));

        if (!reserva.isAtiva()) {
            throw new ReservaException("Reserva já está cancelada");
        }

        try {
            // Buscar livro para liberar
            Livro livro = bibliotecaClient.buscarPorId(reserva.getLivroId())
                    .orElse(null);

            if (livro != null) {
                livro.devolver();
            }

            reserva.cancelar();
            reservaRepository.save(reserva);

            logger.info("✅ Reserva cancelada com sucesso!");

        } catch (Exception e) {
            logger.error("Erro ao cancelar reserva: " + e.getMessage());
            throw new ReservaException("Erro ao cancelar reserva: " + e.getMessage());
        }
    }

    @Override
    public List<ReservaDTO> listarPorDiscente(String matriculaDiscente) {
        logger.info("Listando reservas do discente: " + matriculaDiscente);

        List<Reserva> reservas = reservaRepository.findByMatriculaDiscente(matriculaDiscente);

        return reservas.stream()
                .map(r -> {
                    ReservaDTO dto = mapper.toDTO(r);
                    try {
                        Livro livro = bibliotecaClient.buscarPorId(r.getLivroId()).orElse(null);
                        if (livro != null) {
                            dto.setLivroTitulo(livro.getTitulo());
                        }
                    } catch (Exception e) {
                        logger.warn("Erro ao buscar livro: " + e.getMessage());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> listarTodas() {
        logger.info("Listando todas as reservas...");

        List<Reserva> reservas = reservaRepository.findAll();

        return reservas.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaDTO> listarAtrasadas() {
        logger.info("Listando reservas atrasadas...");

        List<Reserva> reservas = reservaRepository.findAtrasadas();

        return reservas.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }
}
