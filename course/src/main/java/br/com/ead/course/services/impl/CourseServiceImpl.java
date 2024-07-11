package br.com.ead.course.services.impl;

import br.com.ead.course.models.CourseModel;
import br.com.ead.course.models.LessonModel;
import br.com.ead.course.models.ModuloModel;
import br.com.ead.course.repositories.CourseRepository;
import br.com.ead.course.repositories.LessonRepository;
import br.com.ead.course.repositories.ModuloRepository;
import br.com.ead.course.services.CourseService;
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

        List<ModuloModel> moduleModelList = moduloRepository.findAllModulosIntoCourse(courseModel.getCourseId());

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
