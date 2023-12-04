package com.ead.course.repository;

import com.ead.course.model.CourseUserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseUserRespository extends JpaRepository<CourseUserModel, UUID> {
}
