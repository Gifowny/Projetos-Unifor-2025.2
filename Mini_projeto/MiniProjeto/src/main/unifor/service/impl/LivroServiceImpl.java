package service.impl;

import dto.LivroDTO;
import exception.ExternalServiceException;
import exception.LivroNotFoundException;
import external.client.BibliotecaClient;
import mapper.LivroMapper;
import model.Livro;
import service.LivroService;
import util.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de Livro.
 *
 * Padrões aplicados:
 * - GRASP: Controller, Information Expert
 * - SOLID: SRP, DIP
 */
public class LivroServiceImpl implements LivroService {

    private static final Logger logger = Logger.getInstance();
    private final BibliotecaClient bibliotecaClient;
    private final LivroMapper mapper;

    public LivroServiceImpl(BibliotecaClient bibliotecaClient) {
        this.bibliotecaClient = bibliotecaClient;
        this.mapper = new LivroMapper();
    }

    @Override
    public List<LivroDTO> listarTodos() {
        try {
            logger.info("Listando todos os livros...");
            List<Livro> livros = bibliotecaClient.buscarTodos();

            return livros.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erro ao listar livros: " + e.getMessage());
            throw new ExternalServiceException("Biblioteca", "Falha ao buscar livros", e);
        }
    }

    @Override
    public LivroDTO buscarPorId(Long id) {
        try {
            logger.info("Buscando livro por ID: " + id);
            Livro livro = bibliotecaClient.buscarPorId(id)
                    .orElseThrow(() -> new LivroNotFoundException(id));

            return mapper.toDTO(livro);

        } catch (LivroNotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Erro ao buscar livro: " + e.getMessage());
            throw new ExternalServiceException("Biblioteca", "Falha ao buscar livro", e);
        }
    }

    @Override
    public List<LivroDTO> listarDisponiveis() {
        try {
            logger.info("Listando livros disponíveis...");
            List<Livro> livros = bibliotecaClient.buscarDisponiveis();

            return livros.stream()
                    .map(mapper::toDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            logger.error("Erro ao listar livros disponíveis: " + e.getMessage());
            throw new ExternalServiceException("Biblioteca", "Falha ao buscar livros", e);
        }
    }
}
