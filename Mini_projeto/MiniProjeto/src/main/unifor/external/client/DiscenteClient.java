package external.client;


import model.Discente;
import java.util.List;
import java.util.Optional;

public interface DiscenteClient {
    List<Discente> buscarTodos() throws Exception;
    Optional<Discente> buscarPorMatricula(String matricula) throws Exception;
}
