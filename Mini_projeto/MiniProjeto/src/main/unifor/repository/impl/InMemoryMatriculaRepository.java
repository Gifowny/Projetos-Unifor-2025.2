package repository.impl;


import model.Matricula;
import model.StatusMatricula;
import repository.MatriculaRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryMatriculaRepository implements MatriculaRepository {

    private final Map<Long, Matricula> storage = new ConcurrentHashMap<>();

    @Override
    public Matricula save(Matricula matricula) {
        storage.put(matricula.getId(), matricula);
        return matricula;
    }

    @Override
    public Optional<Matricula> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Matricula> findAll() {
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
    public List<Matricula> findByMatriculaDiscente(String matriculaDiscente) {
        return storage.values().stream()
                .filter(m -> m.getMatriculaDiscente().equals(matriculaDiscente))
                .filter(m -> m.getStatus() == StatusMatricula.ATIVA)
                .collect(Collectors.toList());
    }

    @Override
    public List<Matricula> findByDisciplinaId(Long disciplinaId) {
        return storage.values().stream()
                .filter(m -> m.getDisciplinaId().equals(disciplinaId))
                .filter(m -> m.getStatus() == StatusMatricula.ATIVA)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByMatriculaDiscenteAndDisciplinaId(String matricula, Long disciplinaId) {
        return storage.values().stream()
                .anyMatch(m -> m.getMatriculaDiscente().equals(matricula) &&
                        m.getDisciplinaId().equals(disciplinaId) &&
                        m.getStatus() == StatusMatricula.ATIVA);
    }

    @Override
    public long countByMatriculaDiscente(String matriculaDiscente) {
        return storage.values().stream()
                .filter(m -> m.getMatriculaDiscente().equals(matriculaDiscente))
                .filter(m -> m.getStatus() == StatusMatricula.ATIVA)
                .count();
    }
}

