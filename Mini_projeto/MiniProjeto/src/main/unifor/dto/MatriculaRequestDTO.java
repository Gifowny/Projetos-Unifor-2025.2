package dto;


public class MatriculaRequestDTO {
    private String matriculaDiscente;
    private Long disciplinaId;

    public MatriculaRequestDTO() {}

    public MatriculaRequestDTO(String matriculaDiscente, Long disciplinaId) {
        this.matriculaDiscente = matriculaDiscente;
        this.disciplinaId = disciplinaId;
    }

    public String getMatriculaDiscente() { return matriculaDiscente; }
    public void setMatriculaDiscente(String m) { this.matriculaDiscente = m; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long d) { this.disciplinaId = d; }
}
