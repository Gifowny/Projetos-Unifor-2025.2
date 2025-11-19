package validator;


import model.Discente;
import util.StringUtil;

public class DiscenteValidator implements Validator<Discente> {

    @Override
    public ValidationResult validate(Discente discente) {
        ValidationResult result = new ValidationResult();

        if (discente == null) {
            result.addError("Discente não pode ser nulo");
            return result;
        }

        if (StringUtil.isNullOrEmpty(discente.getNome())) {
            result.addError("Nome do discente é obrigatório");
        }

        if (StringUtil.isNullOrEmpty(discente.getMatricula())) {
            result.addError("Matrícula do discente é obrigatória");
        }

        if (StringUtil.isNullOrEmpty(discente.getCurso())) {
            result.addError("Curso do discente é obrigatório");
        }

        if (discente.getSituacaoAcademica() == null) {
            result.addError("Situação acadêmica é obrigatória");
        }

        return result;
    }
}