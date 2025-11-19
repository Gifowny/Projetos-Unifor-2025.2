package mapper;

import dto.MatriculaDTO;
import model.Matricula;
import util.DateUtil;

public class MatriculaMapper implements Mapper<Matricula, MatriculaDTO> {

    @Override
    public MatriculaDTO toDTO(Matricula matricula) {
        if (matricula == null) return null;

        MatriculaDTO dto = new MatriculaDTO();
        dto.setId(matricula.getId());
        dto.setMatriculaDiscente(matricula.getMatriculaDiscente());
        dto.setDisciplinaId(matricula.getDisciplinaId());
        dto.setDataMatricula(DateUtil.format(matricula.getDataMatricula()));
        dto.setStatus(matricula.getStatus().toString());
        dto.setObservacao(matricula.getObservacao());

        return dto;
    }

    @Override
    public Matricula toEntity(MatriculaDTO dto) {
        if (dto == null) return null;

        Matricula matricula = new Matricula();
        matricula.setId(dto.getId());
        matricula.setMatriculaDiscente(dto.getMatriculaDiscente());
        matricula.setDisciplinaId(dto.getDisciplinaId());
        matricula.setObservacao(dto.getObservacao());

        return matricula;
    }
}

