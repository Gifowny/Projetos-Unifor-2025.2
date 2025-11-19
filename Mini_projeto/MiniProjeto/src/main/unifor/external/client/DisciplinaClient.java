package external.client;

import model.Disciplina;
import java.util.List;
import java.util.Optional;

public interface DisciplinaClient {
    List<Disciplina> buscarTodas() throws Exception;
    Optional<Disciplina> buscarPorId(Long id) throws Exception;
    List<Disciplina> buscarPorCurso(String curso) throws Exception;
}
