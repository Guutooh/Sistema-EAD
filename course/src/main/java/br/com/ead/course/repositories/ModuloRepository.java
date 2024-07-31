package br.com.ead.course.repositories;

import br.com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuloRepository extends JpaRepository<ModuleModel, UUID>, JpaSpecificationExecutor<ModuleModel> {


    @Query(value="select * from tb_modules where course_course_id = :courseId", nativeQuery = true)
    List<ModuleModel> findAllModulosIntoCourse(@Param("courseId") UUID courseId);


    @Query(value = "select * from tb_modules where course_course_id = :courseId and module_id = :moduleId", nativeQuery = true)
    Optional<ModuleModel> findModuloIntoCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}

