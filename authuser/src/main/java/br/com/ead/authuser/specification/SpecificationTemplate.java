package br.com.ead.authuser.specification;

import br.com.ead.authuser.models.UserCourseModel;
import br.com.ead.authuser.models.UserModel;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.UUID;

public class SpecificationTemplate {

    @And({
            @Spec(path = "userType", spec = Equal.class),
            @Spec(path = "userStatus", spec = Equal.class),
            @Spec(path = "email", spec = Equal.class),
            @Spec(path = "fullName", spec = LikeIgnoreCase.class)
    })
    public interface UserSpec extends Specification<UserModel> {}



    public static Specification<UserModel> userCourseId(final UUID courseId) {
        return (root, query, cb) -> {
            query.distinct(true);  // Garante que a consulta retorne resultados distintos, eliminando duplicados.
            Join<UserModel, UserCourseModel> userProd = root.join("usersCourses"); // Realiza um join entre UserModel (Entidade A) e UserCourseModel (Entidade B) usando a associação "usersCourses".
            return cb.equal(userProd.get("courseId"), courseId); // Adiciona uma condição na consulta onde o campo "courseId" em UserCourseModel deve ser igual ao courseId fornecido.
        };
    }





}
