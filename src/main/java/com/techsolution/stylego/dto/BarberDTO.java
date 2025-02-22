package com.techsolution.stylego.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BarberDTO {
    private Integer type;
    private Double assessment;
    private Integer numberCuts;
    private UserDTO user;
}
