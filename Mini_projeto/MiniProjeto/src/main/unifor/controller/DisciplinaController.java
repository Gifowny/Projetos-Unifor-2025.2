package controller;


import dto.DisciplinaDTO;
import service.DisciplinaService;
import util.InputValidator;
import view.DisciplinaView;

import java.util.List;

public class DisciplinaController {

    private final DisciplinaService disciplinaService;
    private final DisciplinaView view;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
        this.view = new DisciplinaView();
    }

    public void listarTodas() {
        List<DisciplinaDTO> disciplinas = disciplinaService.listarTodas();
        view.exibirLista(disciplinas);
    }

    public void listarPorCurso() {
        String curso = InputValidator.readString("Digite o nome do curso: ");
        List<DisciplinaDTO> disciplinas = disciplinaService.listarPorCurso(curso);
        view.exibirLista(disciplinas);
    }

    public void buscarPorId() {
        Long id = InputValidator.readLong("Digite o ID da disciplina: ");
        DisciplinaDTO disciplina = disciplinaService.buscarPorId(id);
        view.exibirDetalhes(disciplina);
    }
}
