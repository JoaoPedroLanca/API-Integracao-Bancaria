package com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private LocalDateTime createdAt;


}
