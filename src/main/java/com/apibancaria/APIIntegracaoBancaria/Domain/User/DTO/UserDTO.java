package com.apibancaria.APIIntegracaoBancaria.DTO;

import com.apibancaria.APIIntegracaoBancaria.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    Long id;
    String name;
    String email;
    String password;
    Role role;
    LocalDate createdAt;
    LocalDate updatedAt;
}
