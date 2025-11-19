package external.client.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.ApplicationConfig;
import external.client.BibliotecaClient;
import external.client.HttpClient;
import external.response.LivroResponse;
import model.Livro;
import model.StatusLivro;
import util.Logger;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BibliotecaClientImpl implements BibliotecaClient {

    private static final Logger logger = Logger.getInstance();
    private final HttpClient httpClient;
    private final ApplicationConfig config;
    private final Gson gson;

    public BibliotecaClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.config = ApplicationConfig.getInstance();
        this.gson = new Gson();
    }

    @Override
    public List<Livro> buscarTodos() throws Exception {
        logger.debug("Buscando todos os livros...");
        String url = config.getBibliotecaServiceUrl();
        String response = httpClient.get(url);

        Type listType = new TypeToken<List<LivroResponse>>(){}.getType();
        List<LivroResponse> responses = gson.fromJson(response, listType);

        return responses.stream()
                .map(this::converterParaLivro)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Livro> buscarPorId(Long id) throws Exception {
        logger.debug("Buscando livro por ID: " + id);
        List<Livro> todos = buscarTodos();

        return todos.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Livro> buscarDisponiveis() throws Exception {
        logger.debug("Buscando livros disponíveis...");
        List<Livro> todos = buscarTodos();

        return todos.stream()
                .filter(Livro::isDisponivel)
                .collect(Collectors.toList());
    }

    private Livro converterParaLivro(LivroResponse response) {
        Livro livro = new Livro();
        livro.setId(response.getId());
        livro.setIsbn(response.getIsbn());
        livro.setTitulo(response.getTitulo());
        livro.setAutor(response.getAutor());
        livro.setAno(response.getAno());
        livro.setStatus(StatusLivro.fromString(response.getStatus()));
        livro.setEditora(response.getEditora());
        return livro;
    }
}