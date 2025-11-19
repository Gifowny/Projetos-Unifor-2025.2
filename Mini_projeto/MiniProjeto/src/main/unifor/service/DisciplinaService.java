package service;


import dto.DisciplinaDTO;
import java.util.List;

public interface DisciplinaService {
    List<DisciplinaDTO> listarTodas();
    DisciplinaDTO buscarPorId(Long id);
    List<DisciplinaDTO> listarPorCurso(String curso);
}
