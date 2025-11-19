package exception;

public class LivroNotFoundException extends AcademicGatewayException {

    public LivroNotFoundException(Long id) {
        super("Livro com ID '" + id + "' não encontrado", "LIVRO_NOT_FOUND");
    }
}