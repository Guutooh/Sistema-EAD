package br.com.ead.course.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ModuleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
