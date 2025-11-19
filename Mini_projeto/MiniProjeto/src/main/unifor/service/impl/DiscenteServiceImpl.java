package service.impl;


import dto.DiscenteDTO;
import exception.DiscenteNotFoundException;
import exception.ExternalServiceException;
import external.client.DiscenteClient;
import mapper.DiscenteMapper;
import model.Discente;
import service.DiscenteService;
import util.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Discente.
 *
 * Padrões aplicados:
 * - GRASP: Controller (Service Layer) - Coordena operações de discente
 * - SOLID: SRP - Responsável apenas por lógica de negócio de discente
 * - SOLID: DIP - Depende de abstrações (DiscenteClient, DiscenteMapper)
 */
public class DiscenteServiceImpl implements DiscenteService {

    private static final Logger logger = Logger.getInstance();
    private final DiscenteClient discenteClient;
    private final DiscenteMapper mapper;

    public DiscenteServiceImpl(DiscenteClient discenteClient) {
        this.discenteClient = discenteClient;
        this.mapper = new DiscenteMapper();
    }

    @Override
    public List<DiscenteDTO> listarTodos() {
        try {
            logger.info("Listando todos os discentes...");
            List<Discente> discentes = discenteClient.buscarTodos();

            return discentes.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erro ao listar discentes: " + e.getMessage());
            throw new ExternalServiceException("Discente", "Falha ao buscar discentes", e);
        }
    }

    @Override
    public DiscenteDTO buscarPorMatricula(String matricula) {
        try {
            logger.info("Buscando discente por matrícula: " + matricula);
            Discente discente = discenteClient.buscarPorMatricula(matricula)
                    .orElseThrow(() -> new DiscenteNotFoundException(matricula));

            return mapper.toDTO(discente);

        } catch (DiscenteNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao buscar discente: " + e.getMessage());
            throw new ExternalServiceException("Discente", "Falha ao buscar discente", e);
        }
    }
}