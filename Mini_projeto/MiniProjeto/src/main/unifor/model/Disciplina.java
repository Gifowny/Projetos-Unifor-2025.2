package model;

public class Disciplina {
    private Long id;
    private String nome;
    private String curso;
    private Integer creditos;

    public Disciplina(Long id, String nome, String curso, Integer creditos) {
        this.id = id;
        this.nome = nome;
        this.curso = curso;
        this.creditos = creditos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public Integer getCreditos() {
        return creditos;
    }

}
