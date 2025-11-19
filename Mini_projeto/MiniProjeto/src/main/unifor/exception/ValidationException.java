package exception;


public class ValidationException extends AcademicGatewayException {

    public ValidationException(String message) {
        super(message, "VALIDATION_ERROR");
    }
}