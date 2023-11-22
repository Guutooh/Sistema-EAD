package com.ead.course.service;

import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuloModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface LessonService {
    LessonModel save(LessonModel lessonModel);

    Optional<LessonModel> findLessonIntoModulo(UUID modulosId, UUID lessonId);

    void delete(LessonModel lessonModel);


    List<LessonModel> findAllByModulo(UUID modulosId);
}
