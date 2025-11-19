package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa uma reserva simulada de livro por um discente.
 * Esta é uma simulação local que não persiste nos microsserviços externos.
 *
 * Padrões aplicados:
 * - SOLID: SRP - Responsável apenas por representar dados da reserva
 * - GRASP: Information Expert - Detém as informações sobre a reserva
 */
public class Reserva {

    private Long id;
    private String matriculaDiscente;
    private Long livroId;
    private LocalDateTime dataReserva;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoReal;
    private StatusReserva status;
    private String observacao;

    public Reserva() {
        this.dataReserva = LocalDateTime.now();
        this.dataDevolucaoPrevista = LocalDateTime.now().plusDays(14); // 14 dias de empréstimo
        this.status = StatusReserva.ATIVA;
    }

    public Reserva(Long id, String matriculaDiscente, Long livroId) {
        this.id = id;
        this.matriculaDiscente = matriculaDiscente;
        this.livroId = livroId;
        this.dataReserva = LocalDateTime.now();
        this.dataDevolucaoPrevista = LocalDateTime.now().plusDays(14);
        this.status = StatusReserva.ATIVA;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatriculaDiscente() {
        return matriculaDiscente;
    }

    public void setMatriculaDiscente(String matriculaDiscente) {
        this.matriculaDiscente = matriculaDiscente;
    }

    public Long getLivroId() {
        return livroId;
    }

    public void setLivroId(Long livroId) {
        this.livroId = livroId;
    }

    public LocalDateTime getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(LocalDateTime dataReserva) {
        this.dataReserva = dataReserva;
    }

    public LocalDateTime getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(LocalDateTime dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public LocalDateTime getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public void setDataDevolucaoReal(LocalDateTime dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Cancela a reserva
     */
    public void cancelar() {
        this.status = StatusReserva.CANCELADA;
        this.dataDevolucaoReal = LocalDateTime.now();
    }

    /**
     * Finaliza a reserva (devolução do livro)
     */
    public void devolver() {
        this.status = StatusReserva.DEVOLVIDA;
        this.dataDevolucaoReal = LocalDateTime.now();
    }

    /**
     * Verifica se a reserva está ativa
     * @return true se o status é ATIVA
     */
    public boolean isAtiva() {
        return this.status == StatusReserva.ATIVA;
    }

    /**
     * Verifica se a reserva está atrasada
     * @return true se a data de devolução prevista já passou
     */
    public boolean isAtrasada() {
        return isAtiva() && LocalDateTime.now().isAfter(dataDevolucaoPrevista);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reserva reserva = (Reserva) o;
        return Objects.equals(id, reserva.id) ||
                (Objects.equals(matriculaDiscente, reserva.matriculaDiscente) &&
                        Objects.equals(livroId, reserva.livroId) &&
                        Objects.equals(dataReserva, reserva.dataReserva));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matriculaDiscente, livroId);
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", matriculaDiscente='" + matriculaDiscente + '\'' +
                ", livroId=" + livroId +
                ", dataReserva=" + dataReserva +
                ", status=" + status +
                ", atrasada=" + isAtrasada() +
                '}';
    }
}