package com.ead.course.service.impl;

import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuloModel;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuloRepository;
import com.ead.course.service.ModuloService;
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
