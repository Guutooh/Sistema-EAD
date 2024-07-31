package com.ead.course.controller;

import com.ead.course.dtos.LessonDto;
import com.ead.course.dtos.ModuloDto;
import com.ead.course.model.CourseModel;
import com.ead.course.model.LessonModel;
import com.ead.course.model.ModuloModel;
import com.ead.course.service.LessonService;
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
@Tag(name = "Lesson-Controller")
public class LessonController {


    @Autowired
    LessonService lessonService;

    @Autowired
    ModuloService moduloService;


    @PostMapping("/modulos/{modulosId}/lessons")
    public ResponseEntity<?> cadastrarLesson(@PathVariable(value = "modulosId") UUID modulosId,
                                             @RequestBody @Valid LessonDto lessonDto) {

        Optional<ModuloModel> moduloModelOptional = moduloService.findById(modulosId);
        if (!moduloModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado");
        }

        var lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonDto, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(moduloModelOptional.get());


        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonModel));
    }


    @DeleteMapping("/modulos/{modulosId}/lessons/{lessonId}")
    public ResponseEntity<?> deletarLesson(@PathVariable(value = "courseId") UUID modulosId,
                                           @PathVariable(value = "moduloId") UUID lessonId) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(modulosId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson não encontrado para esse módulo");
        }
        lessonService.delete(lessonModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deletado com sucesso.");
    }

    @PutMapping("/modulos/{modulosId}/lessons/{lessonId}")
    public ResponseEntity<?> atualizarLesson(@PathVariable(value = "courseId") UUID modulosId,
                                             @PathVariable(value = "moduloId") UUID lessonId,
                                             @RequestBody @Valid LessonDto lessonDto) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(modulosId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso.");
        }

        var lessonModulo = lessonModelOptional.get();

        lessonModulo.setTitle(lessonDto.getTitle());
        lessonModulo.setDescription(lessonDto.getDescription());
        lessonDto.setVideoUrl(lessonDto.getVideoUrl());

        return ResponseEntity.status(HttpStatus.OK).body(lessonService.save(lessonModulo));
    }

    @GetMapping("/modulos/{modulosId}/lessons/")
    public ResponseEntity<List<LessonModel>> listarTodosLessons(@PathVariable(value = "modulosId") UUID modulosId){

        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModulo(modulosId));
    }


    @GetMapping("/modulos/{modulosId}/lessons/{lessonId}")
    public ResponseEntity<?> PesquiserLesson(@PathVariable(value = "modulosId") UUID modulosId,
                                             @PathVariable(value = "lessonId") UUID lessonId) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(modulosId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());

    }



}
