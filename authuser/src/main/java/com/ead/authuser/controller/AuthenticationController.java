package com.ead.authuser.controller;

import com.ead.authuser.dto.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.model.UserModel;
import com.ead.authuser.service.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody
                                                  @Validated(UserDto.UserView.RegistrationPost.class)
                                                  @JsonView(UserDto.UserView.RegistrationPost.class) UserDto dto) {

        if (userService.existsByUsername(dto.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já existe");
        }
        if (userService.existsByEmail(dto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já existe");
        }

        var userModel = new UserModel();
        BeanUtils.copyProperties(dto, userModel);

        userModel.setUserStatus(UserStatus.ACTIVE);
        userModel.setUserType(UserType.STUDENT);
        userModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        userModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));

        userService.save(userModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }


}
