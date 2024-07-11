package br.com.ead.course.services;

import br.com.ead.course.models.ModuloModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuloService {

    void deletar(ModuloModel moduloModel);

    ModuloModel save(ModuloModel moduloModel);

    Optional<ModuloModel> findModuloIntoCourse(UUID courseId, UUID moduloId);

    List<ModuloModel> findAllByCourse(UUID courseId);

    Optional<ModuloModel> findById(UUID modulosId);
}
