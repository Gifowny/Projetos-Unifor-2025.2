package model;


/**
 * Enum que representa os status possíveis de um livro no acervo.
 *
 * Padrões aplicados:
 * - GRASP: Information Expert - Encapsula o conhecimento sobre status de livros
 */
public enum StatusLivro {

    DISPONIVEL("Disponível", "Livro disponível para empréstimo/reserva"),
    INDISPONIVEL("Indisponível", "Livro emprestado ou reservado"),
    MANUTENCAO("Em Manutenção", "Livro em processo de reparo ou restauração"),
    EXTRAVIADO("Extraviado", "Livro perdido ou não localizado");

    private final String descricao;
    private final String detalhes;

    StatusLivro(String descricao, String detalhes) {
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
     * @param valor String representando o status
     * @return StatusLivro correspondente
     */
    public static StatusLivro fromString(String valor) {
        if (valor == null) {
            return INDISPONIVEL;
        }

        String valorUpper = valor.toUpperCase().trim();

        switch (valorUpper) {
            case "DISPONIVEL":
            case "DISPONÍVEL":
            case "AVAILABLE":
                return DISPONIVEL;
            case "INDISPONIVEL":
            case "INDISPONÍVEL":
            case "UNAVAILABLE":
            case "EMPRESTADO":
            case "RESERVADO":
                return INDISPONIVEL;
            case "MANUTENCAO":
            case "MANUTENÇÃO":
            case "MAINTENANCE":
                return MANUTENCAO;
            case "EXTRAVIADO":
            case "LOST":
                return EXTRAVIADO;
            default:
                return INDISPONIVEL;
        }
    }

    @Override
    public String toString() {
        return descricao;
    }
}