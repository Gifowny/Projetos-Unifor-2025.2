package controller;

import dto.ReservaDTO;
import dto.ReservaRequestDTO;
import service.ReservaService;
import util.InputValidator;
import view.ReservaView;

import java.util.List;

public class ReservaController {

    private final ReservaService reservaService;
    private final ReservaView view;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
        this.view = new ReservaView();
    }

    public void realizarReserva() {
        view.exibirCabecalho("REALIZAR RESERVA DE LIVRO");

        String matriculaDiscente = InputValidator.readString("Matrícula do discente: ");
        Long livroId = InputValidator.readLong("ID do livro: ");

        ReservaRequestDTO request = new ReservaRequestDTO(matriculaDiscente, livroId);
        ReservaDTO resultado = reservaService.realizarReserva(request);

        view.exibirSucesso(resultado);
    }

    public void cancelarReserva() {
        view.exibirCabecalho("CANCELAR RESERVA");

        Long reservaId = InputValidator.readLong("ID da reserva a cancelar: ");

        if (InputValidator.readConfirmation("Confirma o cancelamento?")) {
            reservaService.cancelarReserva(reservaId);
            System.out.println("\n✅ Reserva cancelada com sucesso!");
        }
    }

    public void listarPorDiscente() {
        String matriculaDiscente = InputValidator.readString("Matrícula do discente: ");
        List<ReservaDTO> reservas = reservaService.listarPorDiscente(matriculaDiscente);
        view.exibirLista(reservas);
    }

    public void listarTodas() {
        List<ReservaDTO> reservas = reservaService.listarTodas();
        view.exibirLista(reservas);
    }

    public void listarAtrasadas() {
        List<ReservaDTO> reservas = reservaService.listarAtrasadas();
        view.exibirListaAtrasadas(reservas);
    }
}
