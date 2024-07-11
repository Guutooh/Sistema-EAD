package br.com.ead.course.services;

import br.com.ead.course.models.LessonModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {
    LessonModel save(LessonModel lessonModel);

    Optional<LessonModel> findLessonIntoModulo(UUID modulosId, UUID lessonId);

    void delete(LessonModel lessonModel);


    List<LessonModel> findAllByModulo(UUID modulosId);
}
