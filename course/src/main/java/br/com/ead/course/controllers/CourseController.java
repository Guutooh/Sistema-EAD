package com.ead.course.controller;

import com.ead.course.dtos.CourseDto;
import com.ead.course.model.CourseModel;
import com.ead.course.service.CourseService;
import com.ead.course.specifications.SpecificationTemplate;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Course-Controller")
public class CourseController {


    @Autowired
    CourseService courseService;

    @PostMapping("/cadastrarCurso")
    public ResponseEntity<?> cadastrarCurso(@RequestBody @Valid CourseDto courseDto) {

        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));


        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

    @DeleteMapping("/deletarCurso/{courseId}")
    public ResponseEntity<?> deletarCurso(@PathVariable(value = "courseId") UUID courseId) {

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }
        courseService.deletar(courseModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Curso deletado com sucesso.");
    }

    @PutMapping("/atualizarCurso")
    public ResponseEntity<?> atualizarCurso(@PathVariable(value = "courseId") UUID courseId,
                                            @RequestBody @Valid CourseDto courseDto) {

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }

        var courseModel = courseModelOptional.get();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
    }

    @GetMapping("/listarTodosCursos")
    public ResponseEntity<Page<CourseModel>> listarTodosCursos(SpecificationTemplate.CourseSpec spec,
                                                               @PageableDefault(page = 0, size = 10, sort = "courseID",
                                                                       direction = Sort.Direction.ASC) Pageable paginacao) {

        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, paginacao));
    }

    @GetMapping("/PesquiserCurso/{courseId}")
    public ResponseEntity<?> PesquiserCurso(@PathVariable(value = "courseId") UUID courseId) {

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");

        }
        return ResponseEntity.status(HttpStatus.OK).body(courseModelOptional.get());

    }

}
