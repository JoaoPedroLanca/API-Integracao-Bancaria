package com.apibancaria.APIIntegracaoBancaria.Domain.User.Mapper;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserRequestDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserResponseDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponse(UserModel userModel){

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(userModel.getId());
        userResponseDTO.setName(userModel.getName());
        userResponseDTO.setEmail(userModel.getEmail());
        userResponseDTO.setRole(userModel.getRole());
        userResponseDTO.setCreatedAt(userModel.getCreatedAt());

        return userResponseDTO;
    }

    public UserModel toModel(UserRequestDTO userRequestDTO){

        UserModel userModel = new UserModel();
        userModel.setName(userRequestDTO.getName());
        userModel.setEmail(userRequestDTO.getEmail());
        userModel.setPassword(userRequestDTO.getPassword());
        userModel.setRole(userRequestDTO.getRole());

        return userModel;
    }
}
