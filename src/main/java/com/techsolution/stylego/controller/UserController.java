package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.UserDTO;
import com.techsolution.stylego.dto.request.UserBarberSaveRequestDTO;
import com.techsolution.stylego.dto.response.UserInfosResponseDTO;
import com.techsolution.stylego.dto.response.UserResponseDTO;
import com.techsolution.stylego.mapper.UserMapper;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.service.BarberSavedUserService;
import com.techsolution.stylego.service.InfosService;
import com.techsolution.stylego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final InfosService infosService;
    private final UserMapper userMapper;
    private final BarberSavedUserService barberSavedUserService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return ResponseEntity.ok(userMapper.userToResponseDto(user));
    }

    @GetMapping("/{userUuid}")
    public ResponseEntity<UserInfosResponseDTO> getUser(@PathVariable String userUuid) {
        UserInfosResponseDTO userInfosResponseDTO = infosService.searchUserAndParameters(userUuid);
        return ResponseEntity.ok(userInfosResponseDTO);
    }


    @PostMapping("/save-barber")
    public ResponseEntity<?> saveBarberUser(@RequestBody UserBarberSaveRequestDTO userBarberSaveDTO) {
        boolean isSaved = barberSavedUserService.saveBarberByUser(userBarberSaveDTO);

        if (isSaved) {
            return ResponseEntity.status(201).body(Map.of("message", "Registration saved successfully!"));
        } else {
            return ResponseEntity.status(409).body(Map.of("message", "The user has already saved this barber previously."));
        }
    }

    @DeleteMapping("/{userUuid}/barbers/{barberUuid}")
    public ResponseEntity<Void> deleteBarberSaved(@PathVariable String userUuid, @PathVariable String barberUuid) {
        barberSavedUserService.deleteBarberByUser(userUuid, barberUuid);
        return ResponseEntity.noContent().build();
    }
}
