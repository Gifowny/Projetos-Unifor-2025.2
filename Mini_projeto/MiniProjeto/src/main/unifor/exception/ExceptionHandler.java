package exception;


import util.Logger;

public class ExceptionHandler {

    private static final Logger logger = Logger.getInstance();

    public static void handleException(Exception e) {
        if (e instanceof DiscenteNotFoundException) {
            logger.error("❌ " + e.getMessage());
        } else if (e instanceof DisciplinaNotFoundException) {
            logger.error("❌ " + e.getMessage());
        } else if (e instanceof LivroNotFoundException) {
            logger.error("❌ " + e.getMessage());
        } else if (e instanceof MatriculaException) {
            logger.error("❌ Erro de Matrícula: " + e.getMessage());
        } else if (e instanceof ReservaException) {
            logger.error("❌ Erro de Reserva: " + e.getMessage());
        } else if (e instanceof ExternalServiceException) {
            ExternalServiceException ese = (ExternalServiceException) e;
            logger.error("⚠️  Serviço " + ese.getServiceName() + " indisponível");
            logger.warn("O sistema continuará operando em modo degradado");
        } else if (e instanceof ValidationException) {
            logger.error("⚠️  Validação: " + e.getMessage());
        } else if (e instanceof BusinessRuleException) {
            logger.error("🚫 Regra de Negócio: " + e.getMessage());
        } else {
            logger.error("❌ Erro inesperado: " + e.getMessage());
            logger.debug("Stack trace: " + e.getClass().getName());
        }
    }

    public static String getFriendlyMessage(Exception e) {
        if (e instanceof ExternalServiceException) {
            return "Serviço temporariamente indisponível. Tente novamente em instantes.";
        } else if (e instanceof ValidationException) {
            return "Dados inválidos: " + e.getMessage();
        } else if (e instanceof BusinessRuleException) {
            return e.getMessage();
        } else {
            return "Ocorreu um erro. Por favor, contate o suporte.";
        }
    }
}

