package br.com.ead.course.services;

import br.com.ead.course.models.LessonModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonService {
    LessonModel save(LessonModel lessonModel);

    Optional<LessonModel> findLessonIntoModulo(UUID modulosId, UUID lessonId);

    void delete(LessonModel lessonModel);


    List<LessonModel> findAllByModule(UUID modulosId);

    Page<LessonModel> findAllByModule(Specification<LessonModel> spec, Pageable pageable);
}
