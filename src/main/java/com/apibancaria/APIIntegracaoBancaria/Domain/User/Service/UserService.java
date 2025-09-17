package com.apibancaria.APIIntegracaoBancaria.Domain.User.Service;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserRequestDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserResponseDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Mapper.UserMapper;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Model.UserModel;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        UserModel userinfo = userMapper.toModel(requestDTO);
        userinfo = userRepository.save(userinfo);
        return userMapper.toResponse(userinfo);
    }

    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO){
        UserModel userExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado em sistema"));

        userExist.setName(requestDTO.getName());
        userExist.setEmail(requestDTO.getEmail());
        userExist.setPassword(requestDTO.getPassword());

        UserModel updatedUser = userRepository.save(userExist);

        return userMapper.toResponse(updatedUser);
    }

    public UserResponseDTO findUserById(Long id){
        Optional<UserModel> userId = userRepository.findById(id);
        return userId.map(userMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado em sistema"));
    }

    public void deleteUserById(Long id){
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuário não encontrado para exclusão");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " não encontrado em sistema"));
        return user;
    }
}
