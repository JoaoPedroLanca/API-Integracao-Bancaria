package com.apibancaria.APIIntegracaoBancaria.Domain.Auth.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequestDTO {

    @NotBlank(message = "The email is required")
    private String email;

    @NotBlank(message = "The password is required")
    private String password;
}
