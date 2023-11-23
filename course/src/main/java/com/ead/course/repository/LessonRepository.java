package com.ead.course.repository;

import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuloModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<LessonModel, UUID>, JpaSpecificationExecutor<LessonModel> {

    @Query(value = "select * from  tb_lessons where module_modulo_id = :moduleId", nativeQuery = true)
    List<LessonModel> findAllLessonsIntoModule(@Param("moduleId")UUID moduleId);


    @Query(value = "select * from  tb_lessons where module_module_id = :moduleId and modulo_id = :lessonId", nativeQuery = true)
    Optional<LessonModel> findLessonIntomodulo(@Param("moduleId")UUID moduleId, @Param("lessonId") UUID lessonId);




}
