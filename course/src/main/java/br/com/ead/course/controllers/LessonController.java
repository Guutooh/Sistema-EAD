package br.com.ead.course.controllers;

import br.com.ead.course.dtos.LessonDto;
import br.com.ead.course.models.LessonModel;
import br.com.ead.course.models.ModuleModel;
import br.com.ead.course.services.LessonService;
import br.com.ead.course.services.ModuleService;
import br.com.ead.course.specifications.SpecificationTemplate;
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

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Lesson-Controller")
public class LessonController {


    @Autowired
    LessonService lessonService;

    @Autowired
    ModuleService moduloService;


    @PostMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<?> saveLesson(@PathVariable UUID moduleId,
                                        @RequestBody @Valid LessonDto lessonDto) {

        Optional<ModuleModel> moduloModelOptional = moduloService.findById(moduleId);

        if (!moduloModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado");
        }

        var lessonModel = new LessonModel();
        BeanUtils.copyProperties(lessonDto, lessonModel);
        lessonModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        lessonModel.setModule(moduloModelOptional.get());


        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.save(lessonModel));
    }


    @DeleteMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<?> deleteLesson(@PathVariable UUID moduleId,
                                          @PathVariable UUID lessonId) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(moduleId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson não encontrado para esse módulo");
        }
        lessonService.delete(lessonModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Lesson deletado com sucesso.");
    }

    @PutMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<?> updateLesson(@PathVariable UUID moduleId,
                                          @PathVariable UUID lessonId,
                                          @RequestBody @Valid LessonDto lessonDto) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(moduleId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lesson não encontrado para esse curso.");
        }

        var lessonModulo = lessonModelOptional.get();

        lessonModulo.setTitle(lessonDto.getTitle());
        lessonModulo.setDescription(lessonDto.getDescription());
        lessonDto.setVideoUrl(lessonDto.getVideoUrl());

        return ResponseEntity.status(HttpStatus.OK).body(lessonService.save(lessonModulo));
    }

    @GetMapping("/modules/{moduleId}/lessons")
    public ResponseEntity<Page<LessonModel>> getAllLessons(@PathVariable(value = "moduleId") UUID moduleId,
                                                           SpecificationTemplate.LessonSpec spec,
                                                           @PageableDefault(page = 0, size = 10, sort = "lessonId", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(lessonService.findAllByModule(SpecificationTemplate.lessonModuleId(moduleId).and(spec), pageable));
    }


    @GetMapping("/modules/{moduleId}/lessons/{lessonId}")
    public ResponseEntity<?> getOneLesson(@PathVariable UUID moduleId,
                                          @PathVariable UUID lessonId) {

        Optional<LessonModel> lessonModelOptional = lessonService.findLessonIntoModulo(moduleId, lessonId);
        if (!lessonModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(lessonModelOptional.get());

    }


}
