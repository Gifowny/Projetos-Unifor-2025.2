package dto;

/**
 * DTO (Data Transfer Object) para transferência de dados de Discente.
 *
 * Padrões aplicados:
 * - Padrão DTO: Separa a representação externa da interna
 * - GRASP: Low Coupling - Reduz acoplamento entre camadas
 * - SOLID: SRP - Responsável apenas por transferir dados
 */
public class DiscenteDTO {

    private Long id;
    private String nome;
    private String matricula;
    private String curso;
    private String modalidade;
    private String situacaoAcademica;

    public DiscenteDTO() {
    }

    public DiscenteDTO(Long id, String nome, String matricula, String curso,
                       String modalidade, String situacaoAcademica) {
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

    public String getSituacaoAcademica() {
        return situacaoAcademica;
    }

    public void setSituacaoAcademica(String situacaoAcademica) {
        this.situacaoAcademica = situacaoAcademica;
    }
}