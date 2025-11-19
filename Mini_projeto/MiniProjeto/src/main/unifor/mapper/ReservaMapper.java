package mapper;

import dto.ReservaDTO;
import model.Reserva;
import util.DateUtil;

public class ReservaMapper implements Mapper<Reserva, ReservaDTO> {

    @Override
    public ReservaDTO toDTO(Reserva reserva) {
        if (reserva == null) return null;

        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        dto.setMatriculaDiscente(reserva.getMatriculaDiscente());
        dto.setLivroId(reserva.getLivroId());
        dto.setDataReserva(DateUtil.format(reserva.getDataReserva()));
        dto.setDataDevolucaoPrevista(DateUtil.format(reserva.getDataDevolucaoPrevista()));
        dto.setDataDevolucaoReal(DateUtil.format(reserva.getDataDevolucaoReal()));
        dto.setStatus(reserva.getStatus().toString());
        dto.setAtrasada(reserva.isAtrasada());

        return dto;
    }

    @Override
    public Reserva toEntity(ReservaDTO dto) {
        if (dto == null) return null;

        Reserva reserva = new Reserva();
        reserva.setId(dto.getId());
        reserva.setMatriculaDiscente(dto.getMatriculaDiscente());
        reserva.setLivroId(dto.getLivroId());

        return reserva;
    }
}