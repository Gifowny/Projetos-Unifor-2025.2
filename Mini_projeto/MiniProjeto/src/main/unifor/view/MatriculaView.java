package view;

import dto.MatriculaDTO;
import java.util.List;

/**
 * View para exibição de informações de Matrículas.
 */
public class MatriculaView extends ConsoleView {

    public void exibirLista(List<MatriculaDTO> matriculas) {
        exibirCabecalho("LISTA DE MATRÍCULAS");

        if (matriculas == null || matriculas.isEmpty()) {
            exibirMensagemVazio("Nenhuma matrícula encontrada.");
            return;
        }

        System.out.printf("%-5s %-15s %-12s %-35s %-18s %-12s%n",
                "ID", "MATRÍCULA", "DISC. ID", "DISCIPLINA", "DATA", "STATUS");
        exibirSeparador();

        for (MatriculaDTO mat : matriculas) {
            System.out.printf("%-5d %-15s %-12d %-35s %-18s %-12s%n",
                    mat.getId(),
                    mat.getMatriculaDiscente(),
                    mat.getDisciplinaId(),
                    truncar(mat.getDisciplinaNome() != null ? mat.getDisciplinaNome() : "N/A", 33),
                    mat.getDataMatricula(),
                    mat.getStatus());
        }

        exibirSeparador();
        System.out.println("Total: " + matriculas.size() + " matrícula(s)");
    }

    public void exibirSucesso(MatriculaDTO matricula) {
        System.out.println();
        exibirLinha();
        System.out.println("✅ MATRÍCULA REALIZADA COM SUCESSO!");
        exibirLinha();
        System.out.println();
        System.out.println("ID da Matrícula:  " + matricula.getId());
        System.out.println("Discente:         " + matricula.getMatriculaDiscente());
        System.out.println("Disciplina:       " + (matricula.getDisciplinaNome() != null ?
                matricula.getDisciplinaNome() : "ID " + matricula.getDisciplinaId()));
        System.out.println("Data:             " + matricula.getDataMatricula());
        System.out.println("Status:           " + matricula.getStatus());
        exibirSeparador();
    }

    private String truncar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho ? texto.substring(0, tamanho - 2) + ".." : texto;
    }
}
