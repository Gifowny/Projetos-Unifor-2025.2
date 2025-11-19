package service;


import dto.DiscenteDTO;
import java.util.List;

public interface DiscenteService {
    List<DiscenteDTO> listarTodos();
    DiscenteDTO buscarPorMatricula(String matricula);
}
