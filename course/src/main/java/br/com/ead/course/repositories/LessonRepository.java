package br.com.ead.course.repositories;

import br.com.ead.course.models.LessonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID> {

    @Query(value = "select * from  tb_lessons where module_modulo_id = :moduleId", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("moduleId")UUID moduleId);


    @Query(value = "select * from  tb_lessons where module_module_id = :moduleId and modulo_id = :lessonId", nativeQuery = true)
    Optional<LessonModel> findLessonIntomodulo(@Param("moduleId")UUID moduleId, @Param("lessonId") UUID lessonId);




}
