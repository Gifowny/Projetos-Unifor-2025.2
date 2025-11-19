package exception;

public class MatriculaException extends AcademicGatewayException {

    public MatriculaException(String message) {
        super(message, "MATRICULA_ERROR");
    }
}