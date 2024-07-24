package br.com.ead.course.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ModuloDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;
}
