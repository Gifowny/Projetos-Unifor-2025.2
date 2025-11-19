package controller;

import dto.LivroDTO;
import service.LivroService;
import util.InputValidator;
import view.LivroView;

import java.util.List;

public class LivroController {

    private final LivroService livroService;
    private final LivroView view;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
        this.view = new LivroView();
    }

    public void listarTodos() {
        List<LivroDTO> livros = livroService.listarTodos();
        view.exibirLista(livros);
    }

    public void listarDisponiveis() {
        List<LivroDTO> livros = livroService.listarDisponiveis();
        view.exibirLista(livros);
    }

    public void buscarPorId() {
        Long id = InputValidator.readLong("Digite o ID do livro: ");
        LivroDTO livro = livroService.buscarPorId(id);
        view.exibirDetalhes(livro);
    }
}
