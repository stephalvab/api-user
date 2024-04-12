package com.bci.apiuser.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private String id;
    private String name;
    private String email;
    private String created;
    private String modified;
    private String last_login;
    private String token;
    private boolean isactive;


}
