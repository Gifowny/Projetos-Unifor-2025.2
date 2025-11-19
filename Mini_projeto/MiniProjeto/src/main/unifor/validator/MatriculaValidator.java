package validator;

import dto.MatriculaRequestDTO;
import util.StringUtil;

public class MatriculaValidator implements Validator<MatriculaRequestDTO> {

    @Override
    public ValidationResult validate(MatriculaRequestDTO request) {
        ValidationResult result = new ValidationResult();

        if (request == null) {
            result.addError("Requisição de matrícula não pode ser nula");
            return result;
        }

        if (StringUtil.isNullOrEmpty(request.getMatriculaDiscente())) {
            result.addError("Matrícula do discente é obrigatória");
        }

        if (request.getDisciplinaId() == null || request.getDisciplinaId() <= 0) {
            result.addError("ID da disciplina inválido");
        }

        return result;
    }
}
