package view;

import dto.ReservaDTO;
import java.util.List;

/**
 * View para exibição de informações de Reservas.
 */
public class ReservaView extends ConsoleView {

    public void exibirLista(List<ReservaDTO> reservas) {
        exibirCabecalho("LISTA DE RESERVAS");

        if (reservas == null || reservas.isEmpty()) {
            exibirMensagemVazio("Nenhuma reserva encontrada.");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-30s %-18s %-18s %-12s%n",
                "ID", "MATRÍCULA", "LIVRO ID", "TÍTULO", "DATA RESERVA", "DEVOLUÇÃO", "STATUS");
        exibirSeparador();

        for (ReservaDTO res : reservas) {
            String status = res.getStatus();
            if (res.isAtrasada()) {
                status = "⚠️ " + status + " (ATRASADA)";
            }

            System.out.printf("%-5d %-15s %-10d %-30s %-18s %-18s %-12s%n",
                    res.getId(),
                    res.getMatriculaDiscente(),
                    res.getLivroId(),
                    truncar(res.getLivroTitulo() != null ? res.getLivroTitulo() : "N/A", 28),
                    res.getDataReserva(),
                    res.getDataDevolucaoPrevista(),
                    status);
        }

        exibirSeparador();
        System.out.println("Total: " + reservas.size() + " reserva(s)");
    }

    public void exibirListaAtrasadas(List<ReservaDTO> reservas) {
        exibirCabecalho("⚠️  RESERVAS ATRASADAS");

        if (reservas == null || reservas.isEmpty()) {
            System.out.println("\n✅ Nenhuma reserva atrasada. Todos os livros foram devolvidos no prazo!");
            return;
        }

        System.out.printf("%-5s %-15s %-10s %-30s %-18s %-18s%n",
                "ID", "MATRÍCULA", "LIVRO ID", "TÍTULO", "DATA RESERVA", "DEVOL. PREVISTA");
        exibirSeparador();

        for (ReservaDTO res : reservas) {
            System.out.printf("%-5d %-15s %-10d %-30s %-18s %-18s%n",
                    res.getId(),
                    res.getMatriculaDiscente(),
                    res.getLivroId(),
                    truncar(res.getLivroTitulo() != null ? res.getLivroTitulo() : "N/A", 28),
                    res.getDataReserva(),
                    res.getDataDevolucaoPrevista());
        }

        exibirSeparador();
        System.out.println("⚠️  Total: " + reservas.size() + " reserva(s) atrasada(s)");
    }

    public void exibirSucesso(ReservaDTO reserva) {
        System.out.println();
        exibirLinha();
        System.out.println("✅ RESERVA REALIZADA COM SUCESSO!");
        exibirLinha();
        System.out.println();
        System.out.println("ID da Reserva:    " + reserva.getId());
        System.out.println("Discente:         " + reserva.getMatriculaDiscente());
        System.out.println("Livro:            " + (reserva.getLivroTitulo() != null ?
                reserva.getLivroTitulo() : "ID " + reserva.getLivroId()));
        System.out.println("Data Reserva:     " + reserva.getDataReserva());
        System.out.println("Devolução:        " + reserva.getDataDevolucaoPrevista());
        System.out.println("Status:           " + reserva.getStatus());
        exibirSeparador();
    }

    private String truncar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho ? texto.substring(0, tamanho - 2) + ".." : texto;
    }
}

