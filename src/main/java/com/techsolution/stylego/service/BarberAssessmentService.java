package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.response.BarberAssessmentResponseDTO;
import com.techsolution.stylego.exception.UserNotFoundException;
import com.techsolution.stylego.model.Barber;
import com.techsolution.stylego.model.BarberAssessment;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.repository.BarberAssessmentRepository;
import com.techsolution.stylego.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarberAssessmentService {

    @Autowired
    private BarberAssessmentRepository barberAssessmentRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private UserService userService;

    public void saveAssessment(BarberAssessmentResponseDTO dto) {
        Barber barber = barberRepository.findByUserUuid(dto.getBarberUuid())
                .orElseThrow(() -> new UserNotFoundException("Barber with UUID " + dto.getBarberUuid() + " not found"));

        User user = userService.searchUserByUuid(dto.getUserUuid());

        BarberAssessment assessment = BarberAssessment.builder()
                .barber(barber)
                .user(user)
                .assessment(dto.getAssessment())
                .build();

        barberAssessmentRepository.save(assessment);
    }

    public Double getBarberAverageAssessment(Long barberId) {
        Double average = barberAssessmentRepository.findAverageAssessmentByBarberId(barberId);
        return (average != null) ? average : 0.0;
    }
}
