package com.apibancaria.APIIntegracaoBancaria.Domain.User.Service;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserDTO;
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

    public UserDTO createUser(UserDTO userDTO){
        UserModel userInfo = userMapper.map(userDTO);
        userInfo = userRepository.save(userInfo);
        return userMapper.map(userInfo);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO){
        Optional<UserModel> userExist = userRepository.findById(id);
        if (userExist.isPresent()) {
            UserModel newUser = userMapper.map(userDTO);
            newUser.setId(id);
            UserModel updateUser = userRepository.save(newUser);
            return userMapper.map(updateUser);
        }
        return null;
    }

    public UserDTO findUserById(Long id){
        Optional<UserModel> userId = userRepository.findById(id);
        return userId.map(userMapper::map).orElse(null);
    }

    public UserDTO deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + " não encontrado em sistema"));
        return user;
    }
}
