package exception;

public class DisciplinaNotFoundException extends AcademicGatewayException {

    public DisciplinaNotFoundException(Long id) {
        super("Disciplina com ID '" + id + "' não encontrada", "DISCIPLINA_NOT_FOUND");
    }

    public DisciplinaNotFoundException(String codigo) {
        super("Disciplina com código '" + codigo + "' não encontrada", "DISCIPLINA_NOT_FOUND");
    }
}