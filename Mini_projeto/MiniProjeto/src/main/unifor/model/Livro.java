package model;

import java.util.Objects;

/**
 * Entidade que representa um livro do acervo da biblioteca.
 *
 * Padrões aplicados:
 * - SOLID: SRP - Responsável apenas por representar dados do livro
 * - GRASP: Information Expert - Detém as informações sobre o livro
 */
public class Livro {

    private Long id;
    private String isbn;
    private String titulo;
    private String autor;
    private Integer ano;
    private StatusLivro status;
    private String editora;
    private String categoria;
    private Integer quantidadeTotal;
    private Integer quantidadeDisponivel;

    public Livro() {
    }

    public Livro(Long id, String isbn, String titulo, String autor,
                 Integer ano, StatusLivro status) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.ano = ano;
        this.status = status;
        this.quantidadeTotal = 1;
        this.quantidadeDisponivel = status == StatusLivro.DISPONIVEL ? 1 : 0;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public StatusLivro getStatus() {
        return status;
    }

    public void setStatus(StatusLivro status) {
        this.status = status;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public void setQuantidadeTotal(Integer quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
    }

    public Integer getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    /**
     * Verifica se o livro está disponível para reserva
     * @return true se o status é DISPONIVEL
     */
    public boolean isDisponivel() {
        return this.status == StatusLivro.DISPONIVEL &&
                (this.quantidadeDisponivel == null || this.quantidadeDisponivel > 0);
    }

    /**
     * Marca o livro como indisponível (reservado)
     */
    public void reservar() {
        this.status = StatusLivro.INDISPONIVEL;
        if (this.quantidadeDisponivel != null && this.quantidadeDisponivel > 0) {
            this.quantidadeDisponivel--;
        }
    }

    /**
     * Marca o livro como disponível (devolução)
     */
    public void devolver() {
        this.status = StatusLivro.DISPONIVEL;
        if (this.quantidadeDisponivel != null) {
            this.quantidadeDisponivel++;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return Objects.equals(id, livro.id) || Objects.equals(isbn, livro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ano=" + ano +
                ", status=" + status +
                ", quantidadeDisponivel=" + quantidadeDisponivel +
                '}';
    }
}

