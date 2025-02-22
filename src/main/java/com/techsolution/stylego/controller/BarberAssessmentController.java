package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.response.BarberAssessmentResponseDTO;
import com.techsolution.stylego.service.BarberAssessmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assessments")
public class BarberAssessmentController {

    private final BarberAssessmentService barberAssessmentService;

    public BarberAssessmentController(BarberAssessmentService barberAssessmentService) {
        this.barberAssessmentService = barberAssessmentService;
    }

    @PostMapping
    public ResponseEntity<Void> createAssessment(@Valid @RequestBody BarberAssessmentResponseDTO dto) {
        barberAssessmentService.saveAssessment(dto);
        return ResponseEntity.ok().build();
    }
}
