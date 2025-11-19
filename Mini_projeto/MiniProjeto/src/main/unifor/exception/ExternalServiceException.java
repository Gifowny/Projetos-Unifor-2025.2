package exception;

public class ExternalServiceException extends AcademicGatewayException {

    private final String serviceName;

    public ExternalServiceException(String serviceName, String message) {
        super("Erro ao acessar serviço " + serviceName + ": " + message, "EXTERNAL_SERVICE_ERROR");
        this.serviceName = serviceName;
    }

    public ExternalServiceException(String serviceName, String message, Throwable cause) {
        super("Erro ao acessar serviço " + serviceName + ": " + message, cause);
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return serviceName;
    }
}