package dto;


public class ReservaRequestDTO {
    private String matriculaDiscente;
    private Long livroId;

    public ReservaRequestDTO() {}

    public ReservaRequestDTO(String matriculaDiscente, Long livroId) {
        this.matriculaDiscente = matriculaDiscente;
        this.livroId = livroId;
    }

    public String getMatriculaDiscente() { return matriculaDiscente; }
    public void setMatriculaDiscente(String m) { this.matriculaDiscente = m; }
    public Long getLivroId() { return livroId; }
    public void setLivroId(Long l) { this.livroId = l; }
}