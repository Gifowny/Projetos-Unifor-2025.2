package repository;

import model.Reserva;
import java.util.List;

public interface ReservaRepository extends Repository<Reserva, Long> {
    List<Reserva> findByMatriculaDiscente(String matriculaDiscente);
    List<Reserva> findByLivroId(Long livroId);
    boolean existsByMatriculaDiscenteAndLivroId(String matricula, Long livroId);
    List<Reserva> findAtrasadas();
}
