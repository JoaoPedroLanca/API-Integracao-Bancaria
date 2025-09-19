package com.apibancaria.APIIntegracaoBancaria.Domain.Auth.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponseDTO {

    private String token;
    private String type = "Bearer";
}
