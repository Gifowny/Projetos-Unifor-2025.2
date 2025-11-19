package dto;

public class MatriculaDTO {
    private Long id;
    private String matriculaDiscente;
    private Long disciplinaId;
    private String disciplinaNome;
    private String dataMatricula;
    private String status;
    private String observacao;

    public MatriculaDTO() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMatriculaDiscente() { return matriculaDiscente; }
    public void setMatriculaDiscente(String m) { this.matriculaDiscente = m; }
    public Long getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(Long d) { this.disciplinaId = d; }
    public String getDisciplinaNome() { return disciplinaNome; }
    public void setDisciplinaNome(String n) { this.disciplinaNome = n; }
    public String getDataMatricula() { return dataMatricula; }
    public void setDataMatricula(String d) { this.dataMatricula = d; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public String getObservacao() { return observacao; }
    public void setObservacao(String o) { this.observacao = o; }
}