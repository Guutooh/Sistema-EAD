package br.com.ead.authuser.controller;

import br.com.ead.authuser.dtos.UserDto;
import br.com.ead.authuser.enums.UserStatus;
import br.com.ead.authuser.enums.UserType;
import br.com.ead.authuser.models.UserModel;
import br.com.ead.authuser.service.UserService;
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
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody
                                          @Validated(UserDto.UserView.RegistrationPost.class)
                                          @JsonView(UserDto.UserView.RegistrationPost.class)
                                          UserDto userDto) {

        log.debug("POST registerUser userDto received {} ", userDto.toString());
        if (userService.existsByUsername(userDto.getUserName())) {

            log.warn("Username {} já existe ", userDto.getUserName());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já existe");
        }
        if (userService.existsByEmail(userDto.getEmail())) {
            log.warn("Email {} já existe ", userDto.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(userDto, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);

        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        log.debug("POST registerUser userId Salvo {} ", userModel.getUserId());
        log.info("Usuario salvo com sucesso userId {} ", userModel.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


    @GetMapping("/")
    public String index() {

        log.trace("TRACE");  // Mais detalhado e geralmente usado para depuração fina.
        log.debug("DEBUG");  // Usado para depuração geral.
        log.info("INFO");    // Informações gerais de operação.
        log.warn("WARN");    // Indicar situações potencialmente prejudiciais.
        log.error("ERROR");  // Erros que podem interromper o funcionamento do aplicativo.

        return "Logging Spring Boot...";
    }


}
