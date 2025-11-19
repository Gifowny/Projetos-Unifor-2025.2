package external.client.impl;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import config.ApplicationConfig;
import external.client.DiscenteClient;
import external.client.HttpClient;
import external.response.DiscenteResponse;
import model.Discente;
import model.SituacaoAcademica;
import util.Logger;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DiscenteClientImpl implements DiscenteClient {

    private static final Logger logger = Logger.getInstance();
    private final HttpClient httpClient;
    private final ApplicationConfig config;
    private final Gson gson;

    public DiscenteClientImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.config = ApplicationConfig.getInstance();
        this.gson = new Gson();
    }

    @Override
    public List<Discente> buscarTodos() throws Exception {
        logger.debug("Buscando todos os discentes...");
        String url = config.getDiscenteServiceUrl();
        String response = httpClient.get(url);

        Type listType = new TypeToken<List<DiscenteResponse>>(){}.getType();
        List<DiscenteResponse> responses = gson.fromJson(response, listType);

        return responses.stream()
                .map(this::converterParaDiscente)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Discente> buscarPorMatricula(String matricula) throws Exception {
        logger.debug("Buscando discente por matrícula: " + matricula);
        List<Discente> todos = buscarTodos();

        return todos.stream()
                .filter(d -> d.getMatricula().equals(matricula))
                .findFirst();
    }

    private Discente converterParaDiscente(DiscenteResponse response) {
        Discente discente = new Discente();
        discente.setId(response.getId());
        discente.setNome(response.getNome());
        discente.setMatricula(response.getMatricula());
        discente.setCurso(response.getCurso());
        discente.setModalidade(response.getModalidade());
        discente.setSituacaoAcademica(
                SituacaoAcademica.fromString(response.getSituacao())
        );
        return discente;
    }
}

