package exception;

public class DiscenteNotFoundException extends AcademicGatewayException {

    public DiscenteNotFoundException(String matricula) {
        super("Discente com matrícula '" + matricula + "' não encontrado", "DISCENTE_NOT_FOUND");
    }
}