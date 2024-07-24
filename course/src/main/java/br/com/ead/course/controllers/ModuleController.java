package com.ead.course.controller;

import com.ead.course.dtos.CourseDto;
import com.ead.course.dtos.ModuloDto;
import com.ead.course.model.CourseModel;
import com.ead.course.model.ModuloModel;
import com.ead.course.service.CourseService;
import com.ead.course.service.ModuloService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Modulo-Controller")
public class ModuloController {


    @Autowired
    ModuloService moduloService;

    @Autowired
    CourseService courseService;

    @PostMapping("/courses/{courseId}/modulos")
    public ResponseEntity<?> cadastrarModulo(@PathVariable(value = "courseId") UUID courseId,
                                             @RequestBody @Valid ModuloDto moduloDto) {

        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }

        var moduloModel = new ModuloModel();
        BeanUtils.copyProperties(moduloDto, moduloModel);
        moduloModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));

        moduloModel.setCourse(courseModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.save(moduloModel));
    }

    @DeleteMapping("/courses/{courseId}/modulos/{moduloId}")
    public ResponseEntity<?> deletarModulo(@PathVariable(value = "courseId") UUID courseId,
                                           @PathVariable(value = "moduloId") UUID moduloId) {

        Optional<ModuloModel> moduloModelOptional = moduloService.findModuloIntoCourse(courseId, moduloId);
        if (!moduloModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso");
        }
        moduloService.deletar(moduloModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("modulo deletado com sucesso.");
    }


    @PutMapping("/courses/{courseId}/modulos/{moduloID}")
    public ResponseEntity<?> atualizarModulo(@PathVariable(value = "courseId") UUID courseId,
                                             @PathVariable(value = "moduloID") UUID moduloID,
                                             @RequestBody @Valid ModuloDto moduloDto) {

        Optional<ModuloModel> moduleModelOptional = moduloService.findModuloIntoCourse(courseId, moduloID);
        if (!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso.");
        }

        var moduleModel = moduleModelOptional.get();

        moduleModel.setTitle(moduloDto.getTitle());
        moduleModel.setDescription(moduloDto.getDescription());

        return ResponseEntity.status(HttpStatus.OK).body(moduloService.save(moduleModel));
    }


    @GetMapping("/courses/{courseId}/modulos")
    public ResponseEntity<List<ModuloModel>> listarTodosModulos(@PathVariable(value = "courseId") UUID courseId) {

        return ResponseEntity.status(HttpStatus.OK).body(moduloService.findAllByCourse(courseId));
    }

    @GetMapping("/courses/{courseId}/modulos/{moduloID}")
    public ResponseEntity<?> PesquiserModulo(@PathVariable(value = "courseId") UUID courseId,
                                             @PathVariable(value = "moduloID") UUID moduloID) {

        Optional<ModuloModel> moduleModelOptional = moduloService.findModuloIntoCourse(courseId, moduloID);
        if (!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modulo não existe para este curso.");

        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());

    }




}
