package external.response;

public class LivroResponse {
    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private Integer ano;
    private String status;
    private String editora;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String t) { this.titulo = t; }
    public String getAutor() { return autor; }
    public void setAutor(String a) { this.autor = a; }
    public Integer getAno() { return ano; }
    public void setAno(Integer ano) { this.ano = ano; }
    public String getStatus() { return status; }
    public void setStatus(String s) { this.status = s; }
    public String getEditora() { return editora; }
    public void setEditora(String e) { this.editora = e; }
}