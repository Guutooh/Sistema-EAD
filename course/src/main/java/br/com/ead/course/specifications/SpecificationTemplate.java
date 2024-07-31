package br.com.ead.course.specifications;

import br.com.ead.course.models.CourseModel;
import br.com.ead.course.models.LessonModel;
import br.com.ead.course.models.ModuleModel;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {


    //Quando a pesquisa for um enum, temos que utilizar o Equal para que a busca seja exata
    @And({
            @Spec(path = "courseLevel", spec = Equal.class),
            @Spec(path = "courseStatus", spec = Equal.class),
            @Spec(path = "name", spec = LikeIgnoreCase.class)
    })
    public interface CourseSpec extends Specification<CourseModel> {
    }


    @And({

            @Spec(path = "title", spec = LikeIgnoreCase.class)
    })
    public interface ModuleSpec extends Specification<ModuleModel> {
    }

    @And({
            @Spec(path = "title", spec = LikeIgnoreCase.class)
    })
    public interface LessonSpec extends Specification<LessonModel> {
    }


    public static Specification<ModuleModel> moduleCourseId(final UUID courseId) {
        return (root, query, cb) -> {
            query.distinct(true); // Remove duplicados da consulta
            Root<ModuleModel> module = root; // Entidade A
            Root<CourseModel> course = query.from(CourseModel.class); // Entidade B
            Expression<Collection<ModuleModel>> coursesModules = course.get("modules"); // Coleção da Entidade A na Entidade B
            return cb.and(cb.equal(course.get("courseId"), courseId), cb.isMember(module, coursesModules)); // Constru
            // ção CriteriaBuilder utilizando AND
        };
    }


    public static Specification<LessonModel> lessonModuleId(final UUID moduleId) {
        return (root, query, cb) -> {
            query.distinct(true);// Remove duplicados da consulta
            Root<LessonModel> lesson = root;// Entidade A
            Root<ModuleModel> module = query.from(ModuleModel.class);// Entidade B
            Expression<Collection<LessonModel>> moduleLessons = module.get("lessons");// Coleção da Entidade A na Entidade B
            return cb.and(cb.equal(module.get("moduleId"), moduleId), cb.isMember(lesson, moduleLessons));// Constru
            // ção CriteriaBuilder utilizando AND
        };
    }



}
