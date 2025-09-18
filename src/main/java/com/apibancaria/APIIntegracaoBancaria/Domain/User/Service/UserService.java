package com.apibancaria.APIIntegracaoBancaria.Domain.User.Service;

import com.apibancaria.APIIntegracaoBancaria.Config.SecurityConfig;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserRequestDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserResponseDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Mapper.UserMapper;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Model.UserModel;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        UserModel userinfo = userMapper.toModel(requestDTO);

        String encryptedPassword = passwordEncoder.encode(requestDTO.getPassword());
        userinfo.setPassword(encryptedPassword);

        userinfo = userRepository.save(userinfo);
        return userMapper.toResponse(userinfo);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO){
        UserModel userExist = userRepository.findById(id).orElse(null);

        String encryptedPassword = passwordEncoder.encode(requestDTO.getPassword());

        userExist.setName(requestDTO.getName());
        userExist.setEmail(requestDTO.getEmail());
        userExist.setPassword(encryptedPassword);

        UserModel updatedUser = userRepository.save(userExist);

        return userMapper.toResponse(updatedUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponseDTO findUserById(Long id){
        Optional<UserModel> userId = userRepository.findById(id);
        return userId.map(userMapper::toResponse).orElse(null);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponseDTO> findAllUsers() {
        List<UserModel> allUsers = userRepository.findAll();
        return allUsers.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " não encontrado em sistema"));
        return user;
    }
}
