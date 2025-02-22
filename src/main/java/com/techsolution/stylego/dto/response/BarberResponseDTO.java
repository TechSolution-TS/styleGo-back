package com.techsolution.stylego.dto.response;

import com.techsolution.stylego.dto.UserDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarberResponseDTO {
    private Integer type;
    private Double assessment;
    private Integer numberCuts;
    private UserResponseDTO user;
}
