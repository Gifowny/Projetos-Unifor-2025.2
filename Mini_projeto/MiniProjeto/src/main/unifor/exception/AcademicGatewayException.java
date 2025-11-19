package exception;

public class AcademicGatewayException extends RuntimeException {

    private final String code;

    public AcademicGatewayException(String message) {
        super(message);
        this.code = "ACADEMIC_ERROR";
    }

    public AcademicGatewayException(String message, String code) {
        super(message);
        this.code = code;
    }

    public AcademicGatewayException(String message, Throwable cause) {
        super(message, cause);
        this.code = "ACADEMIC_ERROR";
    }

    public String getCode() {
        return code;
    }
}
