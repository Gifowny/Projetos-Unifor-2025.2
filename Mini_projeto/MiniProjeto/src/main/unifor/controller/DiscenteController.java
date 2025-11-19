package controller;


import dto.DiscenteDTO;
import service.DiscenteService;
import util.InputValidator;
import view.DiscenteView;

import java.util.List;

/**
 * Controlador para operações relacionadas a Discentes.
 *
 * Padrões aplicados:
 * - MVC: Controller
 * - GRASP: Controller, Low Coupling
 */
public class DiscenteController {

    private final DiscenteService discenteService;
    private final DiscenteView view;

    public DiscenteController(DiscenteService discenteService) {
        this.discenteService = discenteService;
        this.view = new DiscenteView();
    }

    public void listarTodos() {
        List<DiscenteDTO> discentes = discenteService.listarTodos();
        view.exibirLista(discentes);
    }

    public void buscarPorMatricula() {
        String matricula = InputValidator.readString("Digite a matrícula do discente: ");
        DiscenteDTO discente = discenteService.buscarPorMatricula(matricula);
        view.exibirDetalhes(discente);
    }
}

