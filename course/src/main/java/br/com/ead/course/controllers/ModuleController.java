package br.com.ead.course.controllers;

import br.com.ead.course.dtos.ModuleDto;
import br.com.ead.course.models.CourseModel;
import br.com.ead.course.models.ModuleModel;
import br.com.ead.course.services.CourseService;
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
@Tag(name = "Modulo-Controller")
public class ModuleController {


    @Autowired
    ModuleService moduleService;

    @Autowired
    CourseService courseService;

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<?> saveModule(@PathVariable UUID courseId,
                                        @RequestBody @Valid ModuleDto moduleDto) {


        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if (!courseModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso não encontrado");
        }

        var moduleModel = new ModuleModel();

        BeanUtils.copyProperties(moduleDto, moduleModel);

        moduleModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));

        moduleModel.setCourse(courseModelOptional.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));
    }


    @DeleteMapping("/courses/{courseId}/modules/{moduloId}")
    public ResponseEntity<?> deleteModel(@PathVariable UUID courseId,
                                         @PathVariable UUID moduloId) {

        Optional<ModuleModel> moduloModelOptional = moduleService.findModuloIntoCourse(courseId, moduloId);

        if (!moduloModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso");
        }

        moduleService.delete(moduloModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("modulo deletado com sucesso.");
    }


    @PutMapping("/courses/{courseId}/modules/{moduloID}")
    public ResponseEntity<?> updateModule(@PathVariable UUID courseId,
                                          @PathVariable UUID moduloID,
                                          @RequestBody @Valid ModuleDto moduloDto) {

        Optional<ModuleModel> moduleModelOptional = moduleService.findModuloIntoCourse(courseId, moduloID);

        if (!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("modulo não encontrado para esse curso.");
        }

        var moduleModel = moduleModelOptional.get();

        BeanUtils.copyProperties(moduloDto, moduleModel);

//        moduleModel.setTitle(moduloDto.getTitle());
//
//        moduleModel.setDescription(moduloDto.getDescription());

        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleModel));
    }


    /*
    Rever essa questão do specification quando duas tabelas tem um relacionamento, utilizando o criteria builder
     */
    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<Page<ModuleModel>> getAllModules(@PathVariable UUID courseId,
                                                           SpecificationTemplate.ModuleSpec spec,
                                                           @PageableDefault(page = 0, size = 10, sort = "moduleId",
                                                                   direction = Sort.Direction.ASC) Pageable page) {

        return ResponseEntity.status(HttpStatus.OK).body(moduleService.findAllByCourse(SpecificationTemplate.moduleCourseId(courseId).and(spec), page));
    }

    @GetMapping("/courses/{courseId}/modules/{moduloID}")
    public ResponseEntity<?> getOneModule(@PathVariable UUID courseId,
                                          @PathVariable UUID moduloID) {

        Optional<ModuleModel> moduleModelOptional = moduleService.findModuloIntoCourse(courseId, moduloID);

        if (!moduleModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modulo não existe para este curso.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(moduleModelOptional.get());
    }


}
