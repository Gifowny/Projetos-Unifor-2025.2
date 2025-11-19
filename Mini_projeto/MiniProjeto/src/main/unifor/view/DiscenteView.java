package view;


import dto.DiscenteDTO;
import java.util.List;

/**
 * View para exibição de informações de Discentes.
 *
 * Padrões aplicados:
 * - MVC: View - Responsável pela apresentação
 * - SOLID: SRP - Responsável apenas por exibir discentes
 */
public class DiscenteView extends ConsoleView {

    public void exibirLista(List<DiscenteDTO> discentes) {
        exibirCabecalho("LISTA DE DISCENTES");

        if (discentes == null || discentes.isEmpty()) {
            exibirMensagemVazio("Nenhum discente encontrado.");
            return;
        }

        System.out.printf("%-5s %-15s %-40s %-20s %-15s %-12s%n",
                "ID", "MATRÍCULA", "NOME", "CURSO", "MODALIDADE", "SITUAÇÃO");
        exibirSeparador();

        for (DiscenteDTO discente : discentes) {
            System.out.printf("%-5d %-15s %-40s %-20s %-15s %-12s%n",
                    discente.getId(),
                    discente.getMatricula(),
                    truncar(discente.getNome(), 38),
                    truncar(discente.getCurso(), 18),
                    truncar(discente.getModalidade(), 13),
                    discente.getSituacaoAcademica());
        }

        exibirSeparador();
        System.out.println("Total: " + discentes.size() + " discente(s)");
    }

    public void exibirDetalhes(DiscenteDTO discente) {
        exibirCabecalho("DETALHES DO DISCENTE");

        System.out.println("ID:               " + discente.getId());
        System.out.println("Matrícula:        " + discente.getMatricula());
        System.out.println("Nome:             " + discente.getNome());
        System.out.println("Curso:            " + discente.getCurso());
        System.out.println("Modalidade:       " + discente.getModalidade());
        System.out.println("Situação:         " + discente.getSituacaoAcademica());

        exibirSeparador();
    }

    private String truncar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho ? texto.substring(0, tamanho - 2) + ".." : texto;
    }
}

