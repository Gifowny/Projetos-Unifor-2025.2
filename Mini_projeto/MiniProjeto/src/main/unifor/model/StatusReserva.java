package model;

/**
 * Enum que representa os status possíveis de uma reserva de livro.
 *
 * Padrões aplicados:
 * - GRASP: Information Expert - Encapsula o conhecimento sobre status de reservas
 */
public enum StatusReserva {

    ATIVA("Ativa", "Reserva ativa, livro emprestado ao discente"),
    CANCELADA("Cancelada", "Reserva cancelada antes da retirada"),
    DEVOLVIDA("Devolvida", "Livro devolvido à biblioteca"),
    ATRASADA("Atrasada", "Devolução em atraso");

    private final String descricao;
    private final String detalhes;

    StatusReserva(String descricao, String detalhes) {
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
