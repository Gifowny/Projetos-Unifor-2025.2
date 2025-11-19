package model;

import java.util.Objects;

/**
 * Entidade que representa um discente (aluno) do sistema acadêmico.
 *
 * Padrões aplicados:
 * - SOLID: SRP (Single Responsibility Principle) - Responsável apenas por representar dados do discente
 * - GRASP: Information Expert - Detém as informações sobre o discente
 */
public class Discente {

    private Long id;
    private String nome;
    private String matricula;
    private String curso;
    private String modalidade;
    private SituacaoAcademica situacaoAcademica;

    public Discente() {
    }

    public Discente(Long id, String nome, String matricula, String curso,
                    String modalidade, SituacaoAcademica situacaoAcademica) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.curso = curso;
        this.modalidade = modalidade;
        this.situacaoAcademica = situacaoAcademica;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public SituacaoAcademica getSituacaoAcademica() {
        return situacaoAcademica;
    }

    public void setSituacaoAcademica(SituacaoAcademica situacaoAcademica) {
        this.situacaoAcademica = situacaoAcademica;
    }

    /**
     * Verifica se o discente está apto a realizar matrículas
     * @return true se a situação acadêmica é ATIVO
     */
    public boolean isAptoParaMatricula() {
        return this.situacaoAcademica == SituacaoAcademica.ATIVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discente discente = (Discente) o;
        return Objects.equals(matricula, discente.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matricula);
    }

    @Override
    public String toString() {
        return "Discente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", matricula='" + matricula + '\'' +
                ", curso='" + curso + '\'' +
                ", modalidade='" + modalidade + '\'' +
                ", situacaoAcademica=" + situacaoAcademica +
                '}';
    }
}