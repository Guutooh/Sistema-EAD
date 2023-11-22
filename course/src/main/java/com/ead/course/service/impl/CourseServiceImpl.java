package com.ead.course.service.impl;

import com.ead.course.model.CourseModel;
import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuloModel;
import com.ead.course.repository.CourseRepository;
import com.ead.course.repository.LessonRepository;
import com.ead.course.repository.ModuloRepository;
import com.ead.course.service.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ModuloRepository moduloRepository;

    @Autowired
    LessonRepository lessonRepository;
    @Transactional
    @Override
    public void deletar(CourseModel courseModel) {

        List<ModuloModel> moduleModelList = moduloRepository.findAllModulosIntoCourse(courseModel.getCourseID());

        if(!moduleModelList.isEmpty()){

            for(ModuloModel module: moduleModelList){

                List<LessonModel> lessonModelList = lessonRepository.findAllLessonsIntoModule(module.getModuloId());

                if(!lessonModelList.isEmpty()){

                    lessonRepository.deleteAll(lessonModelList);
                }
            }
            moduloRepository.deleteAll(moduleModelList);
        }
        courseRepository.delete(courseModel);
    }

    @Override
    public CourseModel save(CourseModel courseModel) {
        return courseRepository.save(courseModel);
    }

    @Override
    public Optional<CourseModel> findById(UUID courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Page<CourseModel> findAll(Specification<CourseModel> spec, Pageable paginacao) {
        return courseRepository.findAll(spec,paginacao);
    }


}
