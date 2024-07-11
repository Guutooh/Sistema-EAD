package br.com.ead.course.services.impl;

import br.com.ead.course.models.LessonModel;
import br.com.ead.course.models.ModuloModel;
import br.com.ead.course.repositories.LessonRepository;
import br.com.ead.course.repositories.ModuloRepository;
import br.com.ead.course.services.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ModuloServiceImpl implements ModuloService {

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    LessonRepository lessonRepository;

    @Transactional
    @Override
    public void deletar(ModuloModel moduloModel) {

        List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(moduloModel.getModuloId());

        if (!lessonModelList.isEmpty()) {

            lessonRepository.deleteAll(lessonModelList);
        }

        moduloRepository.delete(moduloModel);

    }

    @Override
    public ModuloModel save(ModuloModel moduloModel) {
        return moduloRepository.save(moduloModel);
    }

    @Override
    public Optional<ModuloModel> findModuloIntoCourse(UUID courseId, UUID moduloId) {
        return moduloRepository.findModuloIntoCourse(courseId, moduloId);
    }

    @Override
    public List<ModuloModel> findAllByCourse(UUID courseId) {
        return moduloRepository.findAllModulosIntoCourse(courseId);
    }

    @Override
    public Optional<ModuloModel> findById(UUID modulosId) {
        return moduloRepository.findById(modulosId);
    }
}
