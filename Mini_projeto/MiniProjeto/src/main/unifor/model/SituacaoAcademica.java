package model;

/**
 * Enum que representa as situações acadêmicas possíveis de um discente.
 *
 * Padrões aplicados:
 * - GRASP: Information Expert - Encapsula o conhecimento sobre situações acadêmicas
 */
public enum SituacaoAcademica {

    ATIVO("Ativo", "Discente com matrícula ativa"),
    TRANCADO("Trancado", "Discente com matrícula trancada");

    private final String descricao;
    private final String detalhes;

    SituacaoAcademica(String descricao, String detalhes) {
        this.descricao = descricao;
        this.detalhes = detalhes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getDetalhes() {
        return detalhes;
    }

    /**
     * Converte uma string para o enum correspondente
     * @param valor String representando a situação
     * @return SituacaoAcademica correspondente
     */
    public static SituacaoAcademica fromString(String valor) {
        if (valor == null) {
            return ATIVO;
        }

        String valorUpper = valor.toUpperCase().trim();

        if (valorUpper.equals("ATIVO") || valorUpper.equals("ACTIVE")) {
            return ATIVO;
        } else if (valorUpper.equals("TRANCADO") || valorUpper.equals("TRANCADA") ||
                valorUpper.equals("SUSPENDED")) {
            return TRANCADO;
        }

        return ATIVO; // Default
    }

    @Override
    public String toString() {
        return descricao;
    }
}