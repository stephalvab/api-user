package com.bci.apiuser.controller;

import com.bci.apiuser.controller.UserController;
import com.bci.apiuser.model.dto.UserDto;
import com.bci.apiuser.model.dto.UserResponseDto;
import com.bci.apiuser.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void createUser_Success() throws Exception {
        // Arrange
        UserDto userDto = createUserDto();
        Mono<UserResponseDto> userResponseDto = createUserResponseDto();
        when(userService.saveUser(any(UserDto.class))).thenReturn(userResponseDto);

        // Act
        ResponseEntity<?> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userResponseDto, response.getBody());
    }

    @Test
    public void createUser_InternalServerError() throws Exception {
        // Arrange
        UserDto userDto = createUserDto();
        when(userService.saveUser(any(UserDto.class))).thenThrow(new RuntimeException("Internal Server Error"));

        // Act
        ResponseEntity<?> response = userController.createUser(userDto);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setName("Stephanie Alva");
        userDto.setEmail("steph.alva@example.com");
        return userDto;
    }

    private Mono<UserResponseDto> createUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId("1");
        userResponseDto.setName("Stephanie Alva");
        userResponseDto.setEmail("steph.alva@example.com");

        return Mono.just(userResponseDto);
    }
}

