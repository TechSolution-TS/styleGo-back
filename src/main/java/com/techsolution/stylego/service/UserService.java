package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.UserDTO;
import com.techsolution.stylego.dto.response.UserResponseDTO;
import com.techsolution.stylego.exception.EmailAlreadyExistsException;
import com.techsolution.stylego.exception.UserNotFoundException;
import com.techsolution.stylego.mapper.UserMapper;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public User registerUser(UserDTO userDTO) {
        userRepository.findByEmail(userDTO.getEmail()).ifPresent(user -> {
            throw new EmailAlreadyExistsException("E-mail já cadastrado: " + userDTO.getEmail());
        });

        User user = userMapper.dtoToUser(userDTO);
        return userRepository.save(user);
    }

    public UserResponseDTO searchUser(Long id) {
        User user = searchUserById(id);
        return userMapper.userToResponseDto(user);
    }

    public User searchUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found"));
    }

    public User searchUserByUuid(String uuid) {
      return userRepository.findByUuid(uuid)
              .orElseThrow(() -> new UserNotFoundException("User with UUID " + uuid + " not found"));
    }

    public User findByEmailLogin(String email) {
        return userRepository.findByEmail(email).orElseThrow(
           () -> new UserNotFoundException("Invalid credentials"));
    }
}

