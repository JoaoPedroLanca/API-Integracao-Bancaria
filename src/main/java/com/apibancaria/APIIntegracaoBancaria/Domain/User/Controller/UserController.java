package com.apibancaria.APIIntegracaoBancaria.Domain.User.Controller;

import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserRequestDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.DTO.UserResponseDTO;
import com.apibancaria.APIIntegracaoBancaria.Domain.User.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO newUser = service.createUser(requestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User created:\n" + newUser);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserRequestDTO requestDTO) {
        UserResponseDTO userId = service.findUserById(id);
            if (userId != null) {
                UserResponseDTO updateUser = service.updateUser(id, requestDTO);
                return ResponseEntity
                        .ok("User updated with success:\n" + updateUser);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found to update");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list")
    public ResponseEntity<List<UserResponseDTO>> usersList() {
        List<UserResponseDTO> users = service.findAllUsers();
        return ResponseEntity
                .ok(users);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/list/{id}")
    public ResponseEntity<?> findUserById(Long id) {
        UserResponseDTO userId = service.findUserById(id);
        if (userId != null){
            return ResponseEntity.ok(userId);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        if (service.findUserById(id) != null) {
            service.deleteUserById(id);
            return ResponseEntity
                    .ok("User deleted with success:\n");
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("User not found");
    }

}
