package com.apibancaria.APIIntegracaoBancaria.Mapper;

import com.apibancaria.APIIntegracaoBancaria.DTO.UserDTO;
import com.apibancaria.APIIntegracaoBancaria.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel map(UserDTO userDTO){

        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setNome(userDTO.getNome());
        userModel.setEmail(userDTO.getEmail());
        userModel.setSenha(userDTO.getSenha());
        userModel.setRole(userDTO.getRole());
        userModel.setCreatedAt(userDTO.getCreatedAt());
        userModel.setUpdatedAt(userDTO.getUpdatedAt());

        return userModel;
    }

    public UserDTO map(UserModel userModel){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setNome(userModel.getNome());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setSenha(userModel.getSenha());
        userDTO.setRole(userModel.getRole());
        userDTO.setCreatedAt(userModel.getCreatedAt());
        userDTO.setUpdatedAt(userModel.getUpdatedAt());

        return userDTO;
    }
}
