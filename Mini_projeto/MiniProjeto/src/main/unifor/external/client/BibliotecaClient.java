package external.client;


import model.Livro;
import java.util.List;
import java.util.Optional;

public interface BibliotecaClient {
    List<Livro> buscarTodos() throws Exception;
    Optional<Livro> buscarPorId(Long id) throws Exception;
    List<Livro> buscarDisponiveis() throws Exception;
}