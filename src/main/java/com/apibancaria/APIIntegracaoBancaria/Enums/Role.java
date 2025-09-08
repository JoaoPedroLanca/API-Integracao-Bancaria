package com.apibancaria.APIIntegracaoBancaria.Enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USUARIO,
    ADMIN;

    @Override
    public String getAuthority() {
        return name() ;
    }
}
