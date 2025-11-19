package controller;

import dto.MatriculaDTO;
import dto.MatriculaRequestDTO;
import service.MatriculaService;
import util.InputValidator;
import view.MatriculaView;

import java.util.List;

public class MatriculaController {

    private final MatriculaService matriculaService;
    private final MatriculaView view;

    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
        this.view = new MatriculaView();
    }

    public void realizarMatricula() {
        view.exibirCabecalho("REALIZAR MATRÍCULA");

        String matriculaDiscente = InputValidator.readString("Matrícula do discente: ");
        Long disciplinaId = InputValidator.readLong("ID da disciplina: ");

        MatriculaRequestDTO request = new MatriculaRequestDTO(matriculaDiscente, disciplinaId);
        MatriculaDTO resultado = matriculaService.realizarMatricula(request);

        view.exibirSucesso(resultado);
    }

    public void cancelarMatricula() {
        view.exibirCabecalho("CANCELAR MATRÍCULA");

        Long matriculaId = InputValidator.readLong("ID da matrícula a cancelar: ");

        if (InputValidator.readConfirmation("Confirma o cancelamento?")) {
            matriculaService.cancelarMatricula(matriculaId);
            System.out.println("\n✅ Matrícula cancelada com sucesso!");
        }
    }

    public void listarPorDiscente() {
        String matriculaDiscente = InputValidator.readString("Matrícula do discente: ");
        List<MatriculaDTO> matriculas = matriculaService.listarPorDiscente(matriculaDiscente);
        view.exibirLista(matriculas);
    }

    public void listarTodas() {
        List<MatriculaDTO> matriculas = matriculaService.listarTodas();
        view.exibirLista(matriculas);
    }
}

