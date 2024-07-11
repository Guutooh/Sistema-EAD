package br.com.ead.course.repositories;

import br.com.ead.course.models.ModuloModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuloRepository extends JpaRepository<ModuloModel, UUID> {



    @Query(value = "select * from  tb_modulos where course_courseid = :courseId", nativeQuery = true)
    List<ModuloModel> findAllModulosIntoCourse(@Param("courseId")UUID courseId);


    @Query(value = "select * from  tb_modulos where course_course_id = :courseId and modulo_id = :moduloId", nativeQuery = true)
    Optional<ModuloModel> findModuloIntoCourse(@Param("courseId")UUID courseId,@Param("moduloId") UUID moduloId);
}
