package com.apibancaria.APIIntegracaoBancaria.Domain.User.Mapper;

import com.apibancaria.APIIntegracaoBancaria.DTO.UserDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserModel map(UserDTO userDTO){

        UserModel userModel = new UserModel();
        userModel.setId(userDTO.getId());
        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(userDTO.getPassword());
        userModel.setRole(userDTO.getRole());
        userModel.setCreatedAt(userDTO.getCreatedAt());
        userModel.setUpdatedAt(userDTO.getUpdatedAt());

        return userModel;
    }

    public UserDTO map(UserModel userModel){

        UserDTO userDTO = new UserDTO();
        userDTO.setId(userModel.getId());
        userDTO.setName(userModel.getName());
        userDTO.setEmail(userModel.getEmail());
        userDTO.setPassword(userModel.getPassword());
        userDTO.setRole(userModel.getRole());
        userDTO.setCreatedAt(userModel.getCreatedAt());
        userDTO.setUpdatedAt(userModel.getUpdatedAt());

        return userDTO;
    }
}
