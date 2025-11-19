package model;


import java.util.Objects;

/**
 * Entidade que representa uma disciplina acadêmica.
 *
 * Padrões aplicados:
 * - SOLID: SRP - Responsável apenas por representar dados da disciplina
 * - GRASP: Information Expert - Detém as informações sobre a disciplina
 */
public class Disciplina {

    private Long id;
    private String codigo;
    private String nome;
    private String curso;
    private Integer vagas;
    private Integer vagasOcupadas;
    private String periodo;
    private String professor;

    public Disciplina() {
        this.vagasOcupadas = 0;
    }

    public Disciplina(Long id, String codigo, String nome, String curso, Integer vagas) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.curso = curso;
        this.vagas = vagas;
        this.vagasOcupadas = 0;
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

    /**
     * Calcula o número de vagas disponíveis
     * @return Número de vagas disponíveis
     */
    public Integer getVagasDisponiveis() {
        return vagas - vagasOcupadas;
    }

    /**
     * Verifica se a disciplina possui vagas disponíveis
     * @return true se há vagas disponíveis
     */
    public boolean possuiVagasDisponiveis() {
        return getVagasDisponiveis() > 0;
    }

    /**
     * Incrementa o contador de vagas ocupadas
     */
    public void ocuparVaga() {
        if (possuiVagasDisponiveis()) {
            this.vagasOcupadas++;
        }
    }

    /**
     * Decrementa o contador de vagas ocupadas
     */
    public void liberarVaga() {
        if (this.vagasOcupadas > 0) {
            this.vagasOcupadas--;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disciplina that = (Disciplina) o;
        return Objects.equals(id, that.id) || Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, codigo);
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", curso='" + curso + '\'' +
                ", vagas=" + vagas +
                ", vagasOcupadas=" + vagasOcupadas +
                ", vagasDisponiveis=" + getVagasDisponiveis() +
                '}';
    }
}
