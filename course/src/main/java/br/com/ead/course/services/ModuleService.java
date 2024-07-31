package br.com.ead.course.services;

import br.com.ead.course.models.ModuleModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleService {

    void delete(ModuleModel moduleModel);

    ModuleModel save(ModuleModel moduleModel);

    Optional<ModuleModel> findModuloIntoCourse(UUID courseId, UUID moduloId);

    List<ModuleModel> findAllByCourse(UUID courseId);

    Optional<ModuleModel> findById(UUID modulosId);

    Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec, Pageable page);
}
