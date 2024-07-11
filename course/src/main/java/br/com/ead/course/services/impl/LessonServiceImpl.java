package br.com.ead.course.services.impl;

import br.com.ead.course.models.LessonModel;
import br.com.ead.course.repositories.LessonRepository;
import br.com.ead.course.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    LessonRepository lessonRepository;

    @Override
    public LessonModel save(LessonModel lessonModel) {
        return lessonRepository.save(lessonModel);
    }

    @Override
    public Optional<LessonModel> findLessonIntoModulo(UUID modulosId, UUID lessonId) {
        return lessonRepository.findLessonIntomodulo(modulosId,lessonId);
    }

    @Override
    public void delete(LessonModel lessonModel) {
        lessonRepository.delete(lessonModel);
    }

    @Override
    public List<LessonModel> findAllByModulo(UUID modulosId) {
        return lessonRepository.findAllLessonsIntoModule(modulosId);
    }


}
