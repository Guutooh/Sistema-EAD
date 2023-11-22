package com.ead.course.service;

import com.ead.course.model.ModuloModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ModuloService {

    void deletar(ModuloModel moduloModel);

    ModuloModel save(ModuloModel moduloModel);

    Optional<ModuloModel> findModuloIntoCourse(UUID courseId, UUID moduloId);

    List<ModuloModel> findAllByCourse(UUID courseId);

    Optional<ModuloModel> findById(UUID modulosId);
}
