package br.com.ead.course.services.impl;

import br.com.ead.course.models.LessonModel;
import br.com.ead.course.models.ModuleModel;
import br.com.ead.course.repositories.LessonRepository;
import br.com.ead.course.repositories.ModuloRepository;
import br.com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    ModuloRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void delete(ModuleModel moduleModel) {

        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduleModel.getModuleId());

        if (!lessonModelList.isEmpty()) {

            lessonRepository.deleteAll(lessonModelList);
        }

        moduleRepository.delete(moduleModel);

    }

    @Override
    public ModuleModel save(ModuleModel moduleModel) {
        return moduleRepository.save(moduleModel);
    }

    @Override
    public Optional<ModuleModel> findModuloIntoCourse(UUID courseId, UUID moduloId) {
        return moduleRepository.findModuloIntoCourse(courseId, moduloId);
    }

    @Override
    public List<ModuleModel> findAllByCourse(UUID courseId) {
        return moduleRepository.findAllModulosIntoCourse(courseId);
    }

    @Override
    public Optional<ModuleModel> findById(UUID modulosId) {
        return moduleRepository.findById(modulosId);
    }

    @Override
    public Page<ModuleModel> findAllByCourse(Specification<ModuleModel> spec, Pageable page) {
        return moduleRepository.findAll(spec, page);
    }
}
