package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.request.ServiceRequestDTO;
import com.techsolution.stylego.dto.request.UserBarberSaveRequestDTO;
import com.techsolution.stylego.dto.response.ServiceRequestsResponseDTO;
import com.techsolution.stylego.service.BarberService;
import com.techsolution.stylego.service.RequestedServicesService;
import com.techsolution.stylego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/request-service")
@RequiredArgsConstructor
public class RequestedServicesController {

    private final RequestedServicesService requestedServicesService;
    private final BarberService barberService;
    private final UserService userService;

    @PostMapping()
    public ResponseEntity<?> createRequest(@RequestBody ServiceRequestDTO serviceRequestDTO) {
        boolean isSaved = requestedServicesService.createServiceRequest(serviceRequestDTO);

        if (isSaved) {
            return ResponseEntity.status(201).body(Map.of("message", "Request saved successfully!"));
        } else {
            return ResponseEntity.status(409).body(Map.of("message", "The user already has a request in progress!"));
        }
    }

    @GetMapping("barber/{barberUuid}")
    public ResponseEntity<List<ServiceRequestsResponseDTO>> searchRequestByBarber(@PathVariable String barberUuid) {
        barberService.searchBarberByUuid(barberUuid);
        List<ServiceRequestsResponseDTO> serviceRequestsResponseDTO = requestedServicesService.searchRequestByBarberUuid(barberUuid);

        return ResponseEntity.ok(serviceRequestsResponseDTO);
    }

    @GetMapping("user/{userUuid}")
    public ResponseEntity<ServiceRequestsResponseDTO> searchRequestByUser(@PathVariable String userUuid) {
        userService.searchUserByUuid(userUuid);
        ServiceRequestsResponseDTO serviceRequestsResponseDTO = requestedServicesService.searchRequestByUserUuid(userUuid);

        return ResponseEntity.ok(serviceRequestsResponseDTO);
    }
}
