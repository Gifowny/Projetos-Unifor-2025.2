package service;


import dto.ReservaDTO;
import dto.ReservaRequestDTO;
import java.util.List;

public interface ReservaService {
    ReservaDTO realizarReserva(ReservaRequestDTO request);
    void cancelarReserva(Long reservaId);
    List<ReservaDTO> listarPorDiscente(String matriculaDiscente);
    List<ReservaDTO> listarTodas();
    List<ReservaDTO> listarAtrasadas();
}
