package external.response;

public class DiscenteResponse {
    private Long id;
    private String nome;
    private String matricula;
    private String curso;
    private String modalidade;
    private String situacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String m) { this.matricula = m; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public String getModalidade() { return modalidade; }
    public void setModalidade(String m) { this.modalidade = m; }
    public String getSituacao() { return situacao; }
    public void setSituacao(String s) { this.situacao = s; }
}
