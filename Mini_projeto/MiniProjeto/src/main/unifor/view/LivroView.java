package view;


import dto.LivroDTO;
import java.util.List;

/**
 * View para exibição de informações de Livros.
 */
public class LivroView extends ConsoleView {

    public void exibirLista(List<LivroDTO> livros) {
        exibirCabecalho("LISTA DE LIVROS");

        if (livros == null || livros.isEmpty()) {
            exibirMensagemVazio("Nenhum livro encontrado.");
            return;
        }

        System.out.printf("%-5s %-15s %-40s %-25s %-6s %-12s%n",
                "ID", "ISBN", "TÍTULO", "AUTOR", "ANO", "STATUS");
        exibirSeparador();

        for (LivroDTO livro : livros) {
            String status = livro.getStatus();
            String statusEmoji = status.contains("Disponível") ? "✅" : "❌";

            System.out.printf("%-5d %-15s %-40s %-25s %-6d %s %-10s%n",
                    livro.getId(),
                    truncar(livro.getIsbn(), 13),
                    truncar(livro.getTitulo(), 38),
                    truncar(livro.getAutor(), 23),
                    livro.getAno(),
                    statusEmoji,
                    status);
        }

        exibirSeparador();
        System.out.println("Total: " + livros.size() + " livro(s)");
    }

    public void exibirDetalhes(LivroDTO livro) {
        exibirCabecalho("DETALHES DO LIVRO");

        System.out.println("ID:               " + livro.getId());
        System.out.println("ISBN:             " + livro.getIsbn());
        System.out.println("Título:           " + livro.getTitulo());
        System.out.println("Autor:            " + livro.getAutor());
        System.out.println("Ano:              " + livro.getAno());
        System.out.println("Status:           " + livro.getStatus());

        if (livro.getEditora() != null) {
            System.out.println("Editora:          " + livro.getEditora());
        }

        if (livro.getCategoria() != null) {
            System.out.println("Categoria:        " + livro.getCategoria());
        }

        if (livro.getQuantidadeDisponivel() != null) {
            System.out.println("Qtd. Disponível:  " + livro.getQuantidadeDisponivel());
        }

        exibirSeparador();
    }

    private String truncar(String texto, int tamanho) {
        if (texto == null) return "";
        return texto.length() > tamanho ? texto.substring(0, tamanho - 2) + ".." : texto;
    }
}
