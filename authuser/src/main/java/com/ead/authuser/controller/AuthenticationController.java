package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Log4j2
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody
                                              @Validated(UserDto.UserView.RegistrationPost.class)
                                              @JsonView(UserDto.UserView.RegistrationPost.class) UserDto dto) {

        log.debug("POST cadastrarUsuario userDto received {} ", dto.toString());

        if (userService.existsByUsername(dto.getUserName())) {
            log.warn("POST Usuario: {} já cadastrado  ", dto.getUserName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já existe");
        }
        if (userService.existsByEmail(dto.getEmail())) {
            log.warn("POST Email: {} já cadastrado  ", dto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(dto, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        log.debug("POST -> Registro Usuario - userId salvo: {}", userModel.getUserId());
        log.debug("POST ->  userId: {} salvo com sucesso", userModel.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping("/")
    public String index() {

        log.trace("TRACE"); // Ter visualização de forma mais granular, rastreamento do código.
        log.debug("DEBUG"); // Em ambientes de Dev, para trazer informações relevantes aos processos.
        log.info("INFO");   // Informações do fluxo do sistema.
        log.warn("WARN");    // Avisos de alertas, não é considerado erro.
        log.error("ERROR"); // Aviso de erros, bom utilizar em blocos Try/Catch

        return "Logging Spring Boot";
    }


}
