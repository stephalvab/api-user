package com.bci.apiuser.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PhoneDto {

    @NotBlank(message = "El número de teléfono es obligatorio")
    private String number;

    @NotBlank(message = "El código de ciudad es obligatorio")
    private String citycode;

    @NotBlank(message = "El código de país es obligatorio")
    private String countrycode;

}
