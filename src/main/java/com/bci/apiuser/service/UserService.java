package com.bci.apiuser.service;

import com.bci.apiuser.model.dto.UserDto;
import com.bci.apiuser.model.dto.UserResponseDto;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserResponseDto> saveUser(UserDto userDto);
}
