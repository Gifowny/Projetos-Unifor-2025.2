package validator;

import dto.ReservaRequestDTO;
import util.StringUtil;

public class ReservaValidator implements Validator<ReservaRequestDTO> {

    @Override
    public ValidationResult validate(ReservaRequestDTO request) {
        ValidationResult result = new ValidationResult();

        if (request == null) {
            result.addError("Requisição de reserva não pode ser nula");
            return result;
        }

        if (StringUtil.isNullOrEmpty(request.getMatriculaDiscente())) {
            result.addError("Matrícula do discente é obrigatória");
        }

        if (request.getLivroId() == null || request.getLivroId() <= 0) {
            result.addError("ID do livro inválido");
        }

        return result;
    }
}