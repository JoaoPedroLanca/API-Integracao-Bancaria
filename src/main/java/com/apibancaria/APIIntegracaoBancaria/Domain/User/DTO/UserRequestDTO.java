package com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.Enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;
    private Role role;
}
