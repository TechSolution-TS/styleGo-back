package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.request.AuthRequestDTO;
import com.techsolution.stylego.dto.response.AuthResponseDTO;
import com.techsolution.stylego.service.AuthService;
import com.techsolution.stylego.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) {
        AuthResponseDTO token = authService.login(authRequest);

        if(token != null) {
            return ResponseEntity.ok(token);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
