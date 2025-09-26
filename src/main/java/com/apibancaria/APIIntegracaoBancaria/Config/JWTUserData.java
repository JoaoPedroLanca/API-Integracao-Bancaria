package com.apibancaria.APIIntegracaoBancaria.Config;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.Enums.Role;
import lombok.Builder;

@Builder
public record JWTUserData (Long id,
                           String name,
                           String email,
                           Role role) {


}
