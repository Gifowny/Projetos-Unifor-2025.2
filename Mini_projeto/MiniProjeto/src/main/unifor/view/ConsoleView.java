package view;

public abstract class ConsoleView {

    protected void exibirLinha() {
        System.out.println("=".repeat(80));
    }

    protected void exibirSeparador() {
        System.out.println("-".repeat(80));
    }

    protected void exibirCabecalho(String titulo) {
        System.out.println();
        exibirLinha();
        System.out.println(centralizarTexto(titulo, 80));
        exibirLinha();
        System.out.println();
    }

    protected String centralizarTexto(String texto, int largura) {
        int espacos = (largura - texto.length()) / 2;
        return " ".repeat(Math.max(0, espacos)) + texto;
    }

    protected void exibirMensagemVazio(String mensagem) {
        System.out.println("\n⚠️  " + mensagem);
    }
}