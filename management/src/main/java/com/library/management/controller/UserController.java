package com.library.management.controller;

import com.library.management.dto.UserDTO;
import com.library.management.entity.User;
import com.library.management.mapper.DTOMapper;
import com.library.management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DTOMapper dtoMapper;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(dtoMapper::toUserDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User user = dtoMapper.toUserEntity(userDTO);
        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(dtoMapper.toUserDTO(savedUser));
    }
}
