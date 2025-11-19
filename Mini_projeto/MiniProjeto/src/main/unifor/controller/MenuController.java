package controller;


import exception.ExceptionHandler;
import external.client.*;
import external.client.impl.*;
import repository.impl.*;
import service.*;
import service.impl.*;
import util.InputValidator;
import util.Logger;
import view.*;

/**
 * Controlador principal do menu da aplicação.
 * Coordena todos os outros controladores e gerencia o fluxo principal.
 *
 * Padrões aplicados:
 * - MVC: Controller - Coordena navegação entre telas
 * - GRASP: Controller - Ponto central de controle
 * - SOLID: SRP - Responsável apenas por navegação
 * - GoF: Facade - Fachada para os subsistemas
 */
public class MenuController {

    private static final Logger logger = Logger.getInstance();

    private final DiscenteController discenteController;
    private final DisciplinaController disciplinaController;
    private final LivroController livroController;
    private final MatriculaController matriculaController;
    private final ReservaController reservaController;

    private final MenuView menuView;

    public MenuController() {
        // Inicialização de dependências (Dependency Injection manual)
        HttpClient httpClient = new HttpClientImpl();

        // Clients externos
        DiscenteClient discenteClient = new DiscenteClientImpl(httpClient);
        DisciplinaClient disciplinaClient = new DisciplinaClientImpl(httpClient);
        BibliotecaClient bibliotecaClient = new BibliotecaClientImpl(httpClient);

        // Repositories
        InMemoryMatriculaRepository matriculaRepository = new InMemoryMatriculaRepository();
        InMemoryReservaRepository reservaRepository = new InMemoryReservaRepository();

        // Services
        DiscenteService discenteService = new DiscenteServiceImpl(discenteClient);
        DisciplinaService disciplinaService = new DisciplinaServiceImpl(disciplinaClient);
        LivroService livroService = new LivroServiceImpl(bibliotecaClient);
        MatriculaService matriculaService = new MatriculaServiceImpl(
                matriculaRepository, discenteClient, disciplinaClient);
        ReservaService reservaService = new ReservaServiceImpl(
                reservaRepository, discenteClient, bibliotecaClient);

        // Controllers
        this.discenteController = new DiscenteController(discenteService);
        this.disciplinaController = new DisciplinaController(disciplinaService);
        this.livroController = new LivroController(livroService);
        this.matriculaController = new MatriculaController(matriculaService);
        this.reservaController = new ReservaController(reservaService);

        // View
        this.menuView = new MenuView();

        logger.info("Sistema inicializado com sucesso!");
    }

    public void exibirMenuPrincipal() {
        boolean continuar = true;

        while (continuar) {
            try {
                menuView.exibirMenuPrincipal();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        menuConsultasDiscentes();
                        break;
                    case 2:
                        menuConsultasDisciplinas();
                        break;
                    case 3:
                        menuConsultasLivros();
                        break;
                    case 4:
                        menuMatriculas();
                        break;
                    case 5:
                        menuReservas();
                        break;
                    case 0:
                        if (InputValidator.readConfirmation("Deseja realmente sair?")) {
                            continuar = false;
                            logger.info("Encerrando aplicação...");
                        }
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }

    private void menuConsultasDiscentes() {
        boolean voltar = false;

        while (!voltar) {
            try {
                menuView.exibirMenuDiscentes();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        discenteController.listarTodos();
                        break;
                    case 2:
                        discenteController.buscarPorMatricula();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

                if (!voltar) InputValidator.waitForEnter();

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }

    private void menuConsultasDisciplinas() {
        boolean voltar = false;

        while (!voltar) {
            try {
                menuView.exibirMenuDisciplinas();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        disciplinaController.listarTodas();
                        break;
                    case 2:
                        disciplinaController.listarPorCurso();
                        break;
                    case 3:
                        disciplinaController.buscarPorId();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

                if (!voltar) InputValidator.waitForEnter();

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }

    private void menuConsultasLivros() {
        boolean voltar = false;

        while (!voltar) {
            try {
                menuView.exibirMenuLivros();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        livroController.listarTodos();
                        break;
                    case 2:
                        livroController.listarDisponiveis();
                        break;
                    case 3:
                        livroController.buscarPorId();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

                if (!voltar) InputValidator.waitForEnter();

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }

    private void menuMatriculas() {
        boolean voltar = false;

        while (!voltar) {
            try {
                menuView.exibirMenuMatriculas();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        matriculaController.realizarMatricula();
                        break;
                    case 2:
                        matriculaController.cancelarMatricula();
                        break;
                    case 3:
                        matriculaController.listarPorDiscente();
                        break;
                    case 4:
                        matriculaController.listarTodas();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

                if (!voltar) InputValidator.waitForEnter();

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }

    private void menuReservas() {
        boolean voltar = false;

        while (!voltar) {
            try {
                menuView.exibirMenuReservas();
                int opcao = InputValidator.readInteger("\nEscolha uma opção: ");

                switch (opcao) {
                    case 1:
                        reservaController.realizarReserva();
                        break;
                    case 2:
                        reservaController.cancelarReserva();
                        break;
                    case 3:
                        reservaController.listarPorDiscente();
                        break;
                    case 4:
                        reservaController.listarTodas();
                        break;
                    case 5:
                        reservaController.listarAtrasadas();
                        break;
                    case 0:
                        voltar = true;
                        break;
                    default:
                        System.out.println("\n❌ Opção inválida!");
                }

                if (!voltar) InputValidator.waitForEnter();

            } catch (Exception e) {
                ExceptionHandler.handleException(e);
                InputValidator.waitForEnter();
            }
        }
    }
}

