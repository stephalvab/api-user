package com.bci.apiuser.controller;

import com.bci.apiuser.exception.MessageException;
import com.bci.apiuser.model.dto.UserDto;
import com.bci.apiuser.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Validated
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserDto userDto) {
        try {
            return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(MessageException.Message(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()));
        }
    }
}
