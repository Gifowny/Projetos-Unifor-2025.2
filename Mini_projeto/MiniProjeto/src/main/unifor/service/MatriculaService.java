package service;

import dto.MatriculaDTO;
import dto.MatriculaRequestDTO;
import java.util.List;

public interface MatriculaService {
    MatriculaDTO realizarMatricula(MatriculaRequestDTO request);
    void cancelarMatricula(Long matriculaId);
    List<MatriculaDTO> listarPorDiscente(String matriculaDiscente);
    List<MatriculaDTO> listarTodas();
}
