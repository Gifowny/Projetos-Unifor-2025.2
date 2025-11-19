package mapper;

import dto.DisciplinaDTO;
import model.Disciplina;

public class DisciplinaMapper implements Mapper<Disciplina, DisciplinaDTO> {

    @Override
    public DisciplinaDTO toDTO(Disciplina disciplina) {
        if (disciplina == null) return null;

        DisciplinaDTO dto = new DisciplinaDTO();
        dto.setId(disciplina.getId());
        dto.setCodigo(disciplina.getCodigo());
        dto.setNome(disciplina.getNome());
        dto.setCurso(disciplina.getCurso());
        dto.setVagas(disciplina.getVagas());
        dto.setVagasOcupadas(disciplina.getVagasOcupadas());
        dto.setVagasDisponiveis(disciplina.getVagasDisponiveis());
        dto.setPeriodo(disciplina.getPeriodo());
        dto.setProfessor(disciplina.getProfessor());

        return dto;
    }

    @Override
    public Disciplina toEntity(DisciplinaDTO dto) {
        if (dto == null) return null;

        Disciplina disciplina = new Disciplina();
        disciplina.setId(dto.getId());
        disciplina.setCodigo(dto.getCodigo());
        disciplina.setNome(dto.getNome());
        disciplina.setCurso(dto.getCurso());
        disciplina.setVagas(dto.getVagas());
        disciplina.setVagasOcupadas(dto.getVagasOcupadas());
        disciplina.setPeriodo(dto.getPeriodo());
        disciplina.setProfessor(dto.getProfessor());

        return disciplina;
    }
}
