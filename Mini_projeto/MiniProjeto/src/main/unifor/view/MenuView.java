package view;

public class MenuView extends ConsoleView {

    public void exibirMenuPrincipal() {
        exibirCabecalho("SISTEMA ACADÊMICO - MENU PRINCIPAL");

        System.out.println("📚 CONSULTAS");
        System.out.println("  [1] Discentes");
        System.out.println("  [2] Disciplinas");
        System.out.println("  [3] Livros");
        System.out.println();
        System.out.println("✏️  OPERAÇÕES");
        System.out.println("  [4] Matrículas");
        System.out.println("  [5] Reservas de Livros");
        System.out.println();
        System.out.println("  [0] Sair");
        exibirSeparador();
    }

    public void exibirMenuDiscentes() {
        exibirCabecalho("CONSULTA DE DISCENTES");
        System.out.println("  [1] Listar todos os discentes");
        System.out.println("  [2] Buscar por matrícula");
        System.out.println("  [0] Voltar");
        exibirSeparador();
    }

    public void exibirMenuDisciplinas() {
        exibirCabecalho("CONSULTA DE DISCIPLINAS");
        System.out.println("  [1] Listar todas as disciplinas");
        System.out.println("  [2] Listar por curso");
        System.out.println("  [3] Buscar por ID");
        System.out.println("  [0] Voltar");
        exibirSeparador();
    }

    public void exibirMenuLivros() {
        exibirCabecalho("CONSULTA DE LIVROS");
        System.out.println("  [1] Listar todos os livros");
        System.out.println("  [2] Listar livros disponíveis");
        System.out.println("  [3] Buscar por ID");
        System.out.println("  [0] Voltar");
        exibirSeparador();
    }

    public void exibirMenuMatriculas() {
        exibirCabecalho("GESTÃO DE MATRÍCULAS");
        System.out.println("  [1] Realizar matrícula");
        System.out.println("  [2] Cancelar matrícula");
        System.out.println("  [3] Listar matrículas por discente");
        System.out.println("  [4] Listar todas as matrículas");
        System.out.println("  [0] Voltar");
        exibirSeparador();
    }

    public void exibirMenuReservas() {
        exibirCabecalho("GESTÃO DE RESERVAS");
        System.out.println("  [1] Realizar reserva");
        System.out.println("  [2] Cancelar reserva");
        System.out.println("  [3] Listar reservas por discente");
        System.out.println("  [4] Listar todas as reservas");
        System.out.println("  [5] Listar reservas atrasadas");
        System.out.println("  [0] Voltar");
        exibirSeparador();
    }
}