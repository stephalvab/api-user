package com.bci.apiuser.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.bci.apiuser.model.dto.PhoneDto;
import com.bci.apiuser.model.dto.UserDto;
import com.bci.apiuser.model.dto.UserResponseDto;
import com.bci.apiuser.model.entity.User;
import com.bci.apiuser.repository.UserRepository;
import com.bci.apiuser.service.impl.UserServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void saveUser_Success() {
        // Arrange
        UserDto userDto = createUserDto();
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());
            return user;
        });

        // Act
        UserResponseDto responseDto = userService.saveUser(userDto).block();

        // Assert
        Assertions.assertNotNull(responseDto);
        Assertions.assertNotNull(responseDto.getId());
        Assertions.assertEquals(userDto.getName(), responseDto.getName());
        Assertions.assertEquals(userDto.getEmail(), responseDto.getEmail());
        Assertions.assertTrue(responseDto.isIsactive());
        Assertions.assertNotNull(responseDto.getCreated());
        Assertions.assertNotNull(responseDto.getModified());
    }

    @Test
    public void saveUser_EmailAlreadyExists() {
        // Arrange
        UserDto userDto = createUserDto();
        when(userRepository.existsByEmail(userDto.getEmail())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.saveUser(userDto).block(),
                "El correo ya está registrado");
    }

    private UserDto createUserDto() {
        List<PhoneDto> phones = new ArrayList<>();
        phones.add(new PhoneDto("1234567", "1", "57"));
        return new UserDto("Juan Rodriguez", "juan@rodriguez.org", "hunter2", phones);
    }
}
