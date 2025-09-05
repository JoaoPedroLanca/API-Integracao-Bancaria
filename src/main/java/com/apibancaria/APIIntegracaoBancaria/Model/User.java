package com.apibancaria.APIIntegracaoBancaria.Model;

import com.apibancaria.APIIntegracaoBancaria.Enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;

@Entity
@Table(name = "tb_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String nome;

    String senha;

    @Enumerated(EnumType.STRING)
    Role role;

    LocalDate createdAt;

    LocalDate updatedAt;

}
