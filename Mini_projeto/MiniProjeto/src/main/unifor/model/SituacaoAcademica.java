package model;

public class SituacaoAcademica {
    private String codigo;
    private String descricao;

    public SituacaoAcademica(String codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
