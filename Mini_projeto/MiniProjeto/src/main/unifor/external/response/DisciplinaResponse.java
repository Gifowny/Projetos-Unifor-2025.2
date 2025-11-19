package external.response;

public class DisciplinaResponse {
    private Long id;
    private String codigo;
    private String nome;
    private String curso;
    private Integer vagas;
    private String professor;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String c) { this.codigo = c; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    public Integer getVagas() { return vagas; }
    public void setVagas(Integer v) { this.vagas = v; }
    public String getProfessor() { return professor; }
    public void setProfessor(String p) { this.professor = p; }
}
