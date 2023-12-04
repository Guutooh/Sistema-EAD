package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.ead.authuser.specifications.SpecificationTemplate;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Log4j2
@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public ResponseEntity<Page<UserModel>> ListarUsuarios(SpecificationTemplate.UserSpec spec,
                                                          @PageableDefault(page = 0, size = 10, sort = "userId",
                                                                  direction = Sort.Direction.ASC)
                                                          Pageable paginacao) {

        Page<UserModel> userModelPage = userService.findAll(spec, paginacao);

        if (!userModelPage.isEmpty()) {
            for (UserModel user : userModelPage.toList()) {
                user.add(linkTo(methodOn(UserController.class).buscarPorId(user.getUserId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(userModelPage);
    }

    @GetMapping("/{userid}")
    public ResponseEntity<?> buscarPorId(@PathVariable(value = "userid") UUID userId) {

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deletar(@PathVariable(value = "userid") UUID userId) {

        log.debug("DELETE -> deleteUser userId recebido: {} ", userId);

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if (!userModelOptional.isPresent()) {

            log.debug("DELETE -> deletar userId deletado: {} ", userId);
            log.info("Usuário deletado com sucesso, userId {} ", userId);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }
        userService.delete(userModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso!");
    }


    @PutMapping("/{userid}")
    public ResponseEntity<?> atualizarUsuario(@PathVariable(value = "userid") UUID userId,
                                              @RequestBody
                                              @Validated(UserDto.UserView.UserPut.class)
                                              @JsonView(UserDto.UserView.UserPut.class) UserDto dto) {

        log.debug("PUT -> atualizarUsuario userDto recebido: {}  :  ", dto.toString());
        Optional<UserModel> userModelOptional = userService.findById(userId);

        if (!userModelOptional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");

        } else {

            var userModel = userModelOptional.get();

            userModel.setFullName(dto.getFullName());
            userModel.setPhoneNumber(dto.getPhoneNumber());
            userModel.setCpf(dto.getCpf());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

            userService.save(userModel);
            log.debug("PUT -> userId: {}, Atualizado.   ", userModel.getUserId());
            log.info(" userId: {}, atualizado com sucesso. ", userModel.getUserId());
            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }

    @PutMapping("/{userid}/senha")
    public ResponseEntity<?> atualizarSenha(@PathVariable(value = "userid") UUID userId,
                                            @RequestBody
                                            @Validated(UserDto.UserView.PasswordPut.class)
                                            @JsonView(UserDto.UserView.PasswordPut.class) UserDto dto) {

        log.debug("PUT -> atualizarSenha userDto salvo: {} ", dto.toString());
        Optional<UserModel> Optional = userService.findById(userId);

        if (!Optional.isPresent()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        if (!Optional.get().getPassword().equals(dto.getOldPassword())) {
            log.warn("Senha de userId: {}, incompatível ", userId);
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Senhas diferentes");

        } else {

            var userModel = Optional.get();

            userModel.setPassword(dto.getPassword());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
            userService.save(userModel);

            log.debug("PUT -> atualizarSenha userId: {}, salvo ", userModel.getUserId());
            log.info("Senha de userId: {} , atualizada com sucesso. ", userModel.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body("Senha atualizada");
        }
    }

    @PutMapping("/{userid}/imagem")
    public ResponseEntity<?> atualizarImagem(@PathVariable(value = "userid") UUID userId,
                                             @RequestBody
                                             @Validated(UserDto.UserView.ImagePut.class)
                                             @JsonView(UserDto.UserView.ImagePut.class) UserDto dto) {

        log.debug("PUT -> atualizarImagem userDto recebido: {} ", dto.toString());

        Optional<UserModel> userModelOptional = userService.findById(userId);

        if (!userModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        } else {
            var userModel = userModelOptional.get();
            userModel.setImageUrl(dto.getImageUrl());
            userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

            userService.save(userModel);

            log.debug("PUT -> atualizarImagem userId: {}, salvo ", userModel.getUserId());
            log.info("Imagem de userId {}, atualizada com sucesso ", userModel.getUserId());

            return ResponseEntity.status(HttpStatus.OK).body(userModel);
        }
    }


}
