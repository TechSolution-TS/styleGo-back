package com.techsolution.stylego.mapper;

import com.techsolution.stylego.dto.UserDTO;
import com.techsolution.stylego.dto.response.UserResponseDTO;
import com.techsolution.stylego.model.Address;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.util.Password;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserMapper {

    public User dtoToUser(UserDTO userDTO) {
        String pass = Password.hashPassword(userDTO.getPassword());

        Address address = Address.builder()
                .address(userDTO.getAddress())
                .city(userDTO.getCity())
                .complement(userDTO.getComplement())
                .neighborhood(userDTO.getNeighborhood())
                .number(userDTO.getNumber())
                .state(userDTO.getState())
                .zipCode(userDTO.getZipCode())
                .country(userDTO.getCountry())
                .build();

        return User.builder()
                .name(userDTO.getName())
                .imageUrl(userDTO.getImageUrl())
                .address(address)
                .email(userDTO.getEmail())
                .password(pass)
                .uuid(UUID.randomUUID().toString())
                .build();
    }

    public UserDTO userToDto(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .address(user.getAddress().getAddress())
                .city(user.getAddress().getCity())
                .complement(user.getAddress().getComplement())
                .neighborhood(user.getAddress().getNeighborhood())
                .number(user.getAddress().getNumber())
                .state(user.getAddress().getState())
                .zipCode(user.getAddress().getZipCode())
                .country(user.getAddress().getCountry())
                .uuid(user.getUuid())
                .build();
    }

    public UserResponseDTO userToResponseDto(User user) {
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .uuid(user.getUuid())
                .build();
    }
}
