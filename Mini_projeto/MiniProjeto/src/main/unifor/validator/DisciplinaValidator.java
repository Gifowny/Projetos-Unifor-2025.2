package validator;


import model.Disciplina;
import util.StringUtil;

public class DisciplinaValidator implements Validator<Disciplina> {

    @Override
    public ValidationResult validate(Disciplina disciplina) {
        ValidationResult result = new ValidationResult();

        if (disciplina == null) {
            result.addError("Disciplina não pode ser nula");
            return result;
        }

        if (StringUtil.isNullOrEmpty(disciplina.getNome())) {
            result.addError("Nome da disciplina é obrigatório");
        }

        if (StringUtil.isNullOrEmpty(disciplina.getCurso())) {
            result.addError("Curso da disciplina é obrigatório");
        }

        if (disciplina.getVagas() == null || disciplina.getVagas() < 0) {
            result.addError("Número de vagas inválido");
        }

        return result;
    }
}
