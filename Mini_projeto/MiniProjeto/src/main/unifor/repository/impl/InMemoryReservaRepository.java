package repository.impl;

import model.Reserva;
import model.StatusReserva;
import repository.ReservaRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryReservaRepository implements ReservaRepository {

    private final Map<Long, Reserva> storage = new ConcurrentHashMap<>();

    @Override
    public Reserva save(Reserva reserva) {
        storage.put(reserva.getId(), reserva);
        return reserva;
    }

    @Override
    public Optional<Reserva> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Reserva> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void delete(Long id) {
        storage.remove(id);
    }

    @Override
    public boolean exists(Long id) {
        return storage.containsKey(id);
    }

    @Override
    public List<Reserva> findByMatriculaDiscente(String matriculaDiscente) {
        return storage.values().stream()
                .filter(r -> r.getMatriculaDiscente().equals(matriculaDiscente))
                .filter(r -> r.getStatus() == StatusReserva.ATIVA)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reserva> findByLivroId(Long livroId) {
        return storage.values().stream()
                .filter(r -> r.getLivroId().equals(livroId))
                .filter(r -> r.getStatus() == StatusReserva.ATIVA)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByMatriculaDiscenteAndLivroId(String matricula, Long livroId) {
        return storage.values().stream()
                .anyMatch(r -> r.getMatriculaDiscente().equals(matricula) &&
                        r.getLivroId().equals(livroId) &&
                        r.getStatus() == StatusReserva.ATIVA);
    }

    @Override
    public List<Reserva> findAtrasadas() {
        return storage.values().stream()
                .filter(Reserva::isAtrasada)
                .collect(Collectors.toList());
    }
}
