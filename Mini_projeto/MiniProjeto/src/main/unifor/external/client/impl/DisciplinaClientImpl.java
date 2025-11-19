package external.client.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.ApplicationConfig;
import external.client.DisciplinaClient;
import external.client.HttpClient;
import external.response.DisciplinaResponse;
import model.Disciplina;
import util.Logger;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DisciplinaClientImpl implements DisciplinaClient {

    private static final Logger logger = Logger.getInstance();
    private final HttpClient httpClient;
    private final ApplicationConfig config;
    private final Gson gson;

    public DisciplinaClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.config = ApplicationConfig.getInstance();
        this.gson = new Gson();
    }

    @Override
    public List<Disciplina> buscarTodas() throws Exception {
        logger.debug("Buscando todas as disciplinas...");
        String url = config.getDisciplinaServiceUrl();
        String response = httpClient.get(url);

        Type listType = new TypeToken<List<DisciplinaResponse>>(){}.getType();
        List<DisciplinaResponse> responses = gson.fromJson(response, listType);

        return responses.stream()
                .map(this::converterParaDisciplina)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Disciplina> buscarPorId(Long id) throws Exception {
        logger.debug("Buscando disciplina por ID: " + id);
        List<Disciplina> todas = buscarTodas();

        return todas.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Disciplina> buscarPorCurso(String curso) throws Exception {
        logger.debug("Buscando disciplinas por curso: " + curso);
        List<Disciplina> todas = buscarTodas();

        return todas.stream()
                .filter(d -> d.getCurso().equalsIgnoreCase(curso))
                .collect(Collectors.toList());
    }

    private Disciplina converterParaDisciplina(DisciplinaResponse response) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(response.getId());
        disciplina.setCodigo(response.getCodigo());
        disciplina.setNome(response.getNome());
        disciplina.setCurso(response.getCurso());
        disciplina.setVagas(response.getVagas());
        disciplina.setProfessor(response.getProfessor());
        return disciplina;
    }
}

