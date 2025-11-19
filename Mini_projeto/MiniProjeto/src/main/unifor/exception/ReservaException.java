package exception;

public class ReservaException extends AcademicGatewayException {

    public ReservaException(String message) {
        super(message, "RESERVA_ERROR");
    }
}
