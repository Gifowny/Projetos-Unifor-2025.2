package dto;

/**
 * DTO para transferência de dados de Disciplina.
 *
 * Padrões aplicados:
 * - Padrão DTO: Separa a representação externa da interna
 * - GRASP: Low Coupling
 * - SOLID: SRP
 */
public class DisciplinaDTO {

    private Long id;
    private String codigo;
    private String nome;
    private String curso;
    private Integer vagas;
    private Integer vagasOcupadas;
    private Integer vagasDisponiveis;
    private String periodo;
    private String professor;

    public DisciplinaDTO() {
    }

    public DisciplinaDTO(Long id, String codigo, String nome, String curso, Integer vagas) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.curso = curso;
        this.vagas = vagas;
        this.vagasOcupadas = 0;
        this.vagasDisponiveis = vagas;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Integer getVagasOcupadas() {
        return vagasOcupadas;
    }

    public void setVagasOcupadas(Integer vagasOcupadas) {
        this.vagasOcupadas = vagasOcupadas;
    }

    public Integer getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(Integer vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
}