package com.techsolution.stylego.dto.request;

import com.techsolution.stylego.dto.UserDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class BarberRequestDTO {
    private Integer type;
    private UserDTO user;
}
