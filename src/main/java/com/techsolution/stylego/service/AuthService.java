package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.request.AuthRequestDTO;
import com.techsolution.stylego.dto.response.AuthResponseDTO;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.util.JwtUtil;
import com.techsolution.stylego.util.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtUtil jwtUtil;


    public AuthResponseDTO login(AuthRequestDTO authRequest) {
        User user = userService.findByEmailLogin(authRequest.getUsername());

        if (Password.verifyPassword(authRequest.getPassword(), user.getPassword())) {
            return new AuthResponseDTO(jwtUtil.generateToken(user.getEmail()), user.getUuid());
        }

        return null;
    }
}
