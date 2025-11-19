

import config.ApplicationConfig;
import controller.MenuController;
import exception.ExceptionHandler;
import util.Logger;

/**
 * Classe principal da aplicação Academic Gateway System.
 *
 * Responsável por:
 * - Inicializar a aplicação
 * - Configurar dependências
 * - Iniciar o menu principal
 *
 * Padrões aplicados:
 * - GRASP: Controller - Ponto de entrada da aplicação
 * - SOLID: SRP - Responsável apenas por inicializar o sistema
 */
public class Main {

    private static final Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        logger.info("=".repeat(60));
        logger.info("ACADEMIC GATEWAY SYSTEM - UNIFOR");
        logger.info("Sistema de Gestão Acadêmica - Projeto de Arquitetura");
        logger.info("=".repeat(60));

        try {
            // Inicializa configurações da aplicação
            ApplicationConfig config = ApplicationConfig.getInstance();
            logger.info("Configurações carregadas com sucesso");

            // Inicializa o controlador do menu principal
            MenuController menuController = new MenuController();

            logger.info("Sistema inicializado com sucesso!");
            logger.info("");

            // Inicia o loop principal do menu
            menuController.exibirMenuPrincipal();

        } catch (Exception e) {
            logger.error("Erro crítico ao inicializar aplicação: " + e.getMessage());
            ExceptionHandler.handleException(e);
            System.exit(1);
        } finally {
            logger.info("");
            logger.info("=".repeat(60));
            logger.info("Aplicação encerrada");
            logger.info("=".repeat(60));
        }
    }
}