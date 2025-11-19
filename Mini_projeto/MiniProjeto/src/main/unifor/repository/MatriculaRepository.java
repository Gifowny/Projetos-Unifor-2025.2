package repository;

import model.Matricula;
import java.util.List;

public interface MatriculaRepository extends Repository<Matricula, Long> {
    List<Matricula> findByMatriculaDiscente(String matriculaDiscente);
    List<Matricula> findByDisciplinaId(Long disciplinaId);
    boolean existsByMatriculaDiscenteAndDisciplinaId(String matricula, Long disciplinaId);
    long countByMatriculaDiscente(String matriculaDiscente);
}