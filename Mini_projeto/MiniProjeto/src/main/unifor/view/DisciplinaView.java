package view;

import dto.DisciplinaDTO;
import java.util.List;

/**
 * View para exibição de informações de Disciplinas.
 */
public class DisciplinaView extends ConsoleView {

    public void exibirLista(List<DisciplinaDTO> disciplinas) {
        exibirCabecalho("LISTA DE DISCIPLINAS");

        if (disciplinas == null || disciplinas.isEmpty()) {
            exibirMensagemVazio("Nenhuma disciplina encontrada.");
            return;
        }

        System.out.printf("%-5s %-10s %-40s %-20s %-8s %-8s%n",
                "ID", "CÓDIGO", "NOME", "CURSO", "VAGAS", "DISP.");
        exibirSeparador();

        for (DisciplinaDTO disc : disciplinas) {
            String vagas = String.format("%d/%d",
                    (disc.getVagas() - disc.getVagasOcupadas()), disc.getVagas());

            System.out.printf("%-5d %-10s %-40s %-20s %-8s %-8d%n",
                    disc.getId(),
                    truncar(disc.getCodigo(), 8),
                    truncar(disc.getNome(), 38),
                    truncar(disc.getCurso(), 18),
                    vagas,
                    disc.getVagasDisponiveis());
        }

        exibirSeparador();
        System.out.println("Total: " + disciplinas.size() + " disciplina(s)");
    }

    public void exibirDetalhes(DisciplinaDTO disciplina) {
        exibirCabecalho("DETALHES DA DISCIPLINA");

        System.out.println("ID:               " + disciplina.getId());
        System.out.println("Código:           " + disciplina.getCodigo());
        System.out.println("Nome:             " + disciplina.getNome());
        System.out.println("Curso:            " + disciplina.getCurso());
        System.out.println("Total de Vagas:   " + disciplina.getVagas());
        System.out.println("Vagas Ocupadas:   " + disciplina.getVagasOcupadas());
        System.out.println("Vagas Disponíveis:" + disciplina.getVagasDisponiveis());

        if (disciplina.getProfessor() != null) {
            System.out.println("Professor:        " + disciplina.getProfessor());
        }

        if (disciplina.getPeriodo() != null) {
            System.out.println("Período:          " + disciplina.getPeriodo());
        }

        exibirSeparador();
    }

    private String truncar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho ? texto.substring(0, tamanho - 2) + ".." : texto;
    }
}
