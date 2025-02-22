package com.techsolution.stylego.mapper;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.UserDTO;
import com.techsolution.stylego.dto.response.BarberResponseDTO;
import com.techsolution.stylego.dto.response.UserResponseDTO;
import com.techsolution.stylego.model.Barber;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.service.BarberAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BarberMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BarberAssessmentService barberAssessmentService;

    public Barber dtoToBarber(User user) {
        return Barber.builder()
                .numberCuts(0)
                .type(1)
                .user(user)
                .online(false)
                .build();
    }

    public BarberResponseDTO barberToResponseDTO(Barber barber, UserResponseDTO dto) {
        Double assessment = barberAssessmentService.getBarberAverageAssessment(barber.getId());

        return BarberResponseDTO.builder()
                .type(barber.getType())
                .assessment(assessment)
                .numberCuts(barber.getNumberCuts())
                .user(dto)
                .build();
    }

    public BarberDTO barberUserToDTO(Barber barber) {
        UserDTO userDTO = userMapper.userToDto(barber.getUser());
        Double assessment = barberAssessmentService.getBarberAverageAssessment(barber.getId());

        return BarberDTO.builder()
                .type(barber.getType())
                .assessment(assessment)
                .numberCuts(barber.getNumberCuts())
                .user(userDTO)
                .build();
    }
}
