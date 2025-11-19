package service.impl;


import dto.DisciplinaDTO;
import exception.DisciplinaNotFoundException;
import exception.ExternalServiceException;
import external.client.DisciplinaClient;
import mapper.DisciplinaMapper;
import model.Disciplina;
import service.DisciplinaService;
import util.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Disciplina.
 *
 * Padrões aplicados:
 * - GRASP: Controller, Information Expert
 * - SOLID: SRP, DIP
 */
public class DisciplinaServiceImpl implements DisciplinaService {

    private static final Logger logger = Logger.getInstance();
    private final DisciplinaClient disciplinaClient;
    private final DisciplinaMapper mapper;

    public DisciplinaServiceImpl(DisciplinaClient disciplinaClient) {
        this.disciplinaClient = disciplinaClient;
        this.mapper = new DisciplinaMapper();
    }

    @Override
    public List<DisciplinaDTO> listarTodas() {
        try {
            logger.info("Listando todas as disciplinas...");
            List<Disciplina> disciplinas = disciplinaClient.buscarTodas();

            return disciplinas.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erro ao listar disciplinas: " + e.getMessage());
            throw new ExternalServiceException("Disciplina", "Falha ao buscar disciplinas", e);
        }
    }

    @Override
    public DisciplinaDTO buscarPorId(Long id) {
        try {
            logger.info("Buscando disciplina por ID: " + id);
            Disciplina disciplina = disciplinaClient.buscarPorId(id)
                    .orElseThrow(() -> new DisciplinaNotFoundException(id));

            return mapper.toDTO(disciplina);

        } catch (DisciplinaNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao buscar disciplina: " + e.getMessage());
            throw new ExternalServiceException("Disciplina", "Falha ao buscar disciplina", e);
        }
    }

    @Override
    public List<DisciplinaDTO> listarPorCurso(String curso) {
        try {
            logger.info("Listando disciplinas do curso: " + curso);
            List<Disciplina> disciplinas = disciplinaClient.buscarPorCurso(curso);

            return disciplinas.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erro ao listar disciplinas por curso: " + e.getMessage());
            throw new ExternalServiceException("Disciplina", "Falha ao buscar disciplinas", e);
        }
    }
}
