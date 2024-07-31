package br.com.ead.course.dtos;

import br.com.ead.course.enums.CourseLevel;
import br.com.ead.course.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class CourseDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private  String imageUrl;

    @NotNull
    private CourseStatus courseStatus;

    @NotNull
    private UUID userInstructor;

    @NotNull
    private CourseLevel courseLevel;

}
