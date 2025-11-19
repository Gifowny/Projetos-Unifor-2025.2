package service;

import dto.LivroDTO;
import java.util.List;

public interface LivroService {
    List<LivroDTO> listarTodos();
    LivroDTO buscarPorId(Long id);
    List<LivroDTO> listarDisponiveis();
}
