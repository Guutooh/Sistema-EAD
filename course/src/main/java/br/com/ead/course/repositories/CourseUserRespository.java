package br.com.ead.course.repositories;

import br.com.ead.course.models.CourseUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseUserRespository extends JpaRepository<CourseUserModel, UUID> {
}
