package mapper;

import dto.DiscenteDTO;
import model.Discente;
import model.SituacaoAcademica;

public class DiscenteMapper implements Mapper<Discente, DiscenteDTO> {

    @Override
    public DiscenteDTO toDTO(Discente discente) {
        if (discente == null) return null;

        DiscenteDTO dto = new DiscenteDTO();
        dto.setId(discente.getId());
        dto.setNome(discente.getNome());
        dto.setMatricula(discente.getMatricula());
        dto.setCurso(discente.getCurso());
        dto.setModalidade(discente.getModalidade());
        dto.setSituacaoAcademica(discente.getSituacaoAcademica().toString());

        return dto;
    }

    @Override
    public Discente toEntity(DiscenteDTO dto) {
        if (dto == null) return null;

        Discente discente = new Discente();
        discente.setId(dto.getId());
        discente.setNome(dto.getNome());
        discente.setMatricula(dto.getMatricula());
        discente.setCurso(dto.getCurso());
        discente.setModalidade(dto.getModalidade());
        discente.setSituacaoAcademica(
                SituacaoAcademica.fromString(dto.getSituacaoAcademica())
        );

        return discente;
    }
}