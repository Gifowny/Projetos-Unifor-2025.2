package dto;


public class ReservaDTO {
    private Long id;
    private String matriculaDiscente;
    private Long livroId;
    private String livroTitulo;
    private String dataReserva;
    private String dataDevolucaoPrevista;
    private String dataDevolucaoReal;
    private String status;
    private boolean atrasada;

    public ReservaDTO() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getMatriculaDiscente() { return matriculaDiscente; }
    public void setMatriculaDiscente(String m) { this.matriculaDiscente = m; }
    public Long getLivroId() { return livroId; }
    public void setLivroId(Long l) { this.livroId = l; }
    public String getLivroTitulo() { return livroTitulo; }
    public void setLivroTitulo(String t) { this.livroTitulo = t; }
    public String getDataReserva() { return dataReserva; }
    public void setDataReserva(String d) { this.dataReserva = d; }
    public String getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public void setDataDevolucaoPrevista(String d) { this.dataDevolucaoPrevista = d; }
    public String getDataDevolucaoReal() { return dataDevolucaoReal; }
    public void setDataDevolucaoReal(String d) { this.dataDevolucaoReal = d; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public boolean isAtrasada() { return atrasada; }
    public void setAtrasada(boolean a) { this.atrasada = a; }
}
