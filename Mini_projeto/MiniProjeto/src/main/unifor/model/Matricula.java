package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Entidade que representa uma matrícula simulada de discente em disciplina.
 * Esta é uma simulação local que não persiste nos microsserviços externos.
 *
 * Padrões aplicados:
 * - SOLID: SRP - Responsável apenas por representar dados da matrícula
 * - GRASP: Information Expert - Detém as informações sobre a matrícula
 */
public class Matricula {

    private Long id;
    private String matriculaDiscente;
    private Long disciplinaId;
    private LocalDateTime dataMatricula;
    private StatusMatricula status;
    private String observacao;

    public Matricula() {
        this.dataMatricula = LocalDateTime.now();
        this.status = StatusMatricula.ATIVA;
    }

    public Matricula(Long id, String matriculaDiscente, Long disciplinaId) {
        this.id = id;
        this.matriculaDiscente = matriculaDiscente;
        this.disciplinaId = disciplinaId;
        this.dataMatricula = LocalDateTime.now();
        this.status = StatusMatricula.ATIVA;
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

    public Long getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(Long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public LocalDateTime getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDateTime dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public StatusMatricula getStatus() {
        return status;
    }

    public void setStatus(StatusMatricula status) {
        this.status = status;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    /**
     * Cancela a matrícula
     */
    public void cancelar() {
        this.status = StatusMatricula.CANCELADA;
    }

    /**
     * Verifica se a matrícula está ativa
     * @return true se o status é ATIVA
     */
    public boolean isAtiva() {
        return this.status == StatusMatricula.ATIVA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(id, matricula.id) ||
                (Objects.equals(matriculaDiscente, matricula.matriculaDiscente) &&
                        Objects.equals(disciplinaId, matricula.disciplinaId));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, matriculaDiscente, disciplinaId);
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", matriculaDiscente='" + matriculaDiscente + '\'' +
                ", disciplinaId=" + disciplinaId +
                ", dataMatricula=" + dataMatricula +
                ", status=" + status +
                '}';
    }
}