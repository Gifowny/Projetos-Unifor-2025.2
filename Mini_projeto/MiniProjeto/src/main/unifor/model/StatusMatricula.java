package model;

/**
 * Enum que representa os status possíveis de uma matrícula.
 *
 * Padrões aplicados:
 * - GRASP: Information Expert - Encapsula o conhecimento sobre status de matrículas
 */
public enum StatusMatricula {

    ATIVA("Ativa", "Matrícula ativa e vigente"),
    CANCELADA("Cancelada", "Matrícula cancelada pelo discente"),
    TRANCADA("Trancada", "Matrícula temporariamente suspensa"),
    CONCLUIDA("Concluída", "Disciplina concluída com êxito");

    private final String descricao;
    private final String detalhes;

    StatusMatricula(String descricao, String detalhes) {
        this.descricao = descricao;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    @Override
    public String toString() {
        return descricao;
    }
}

