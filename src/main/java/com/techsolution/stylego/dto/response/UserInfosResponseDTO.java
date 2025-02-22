package com.techsolution.stylego.dto.response;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfosResponseDTO {

    private UserDTO user;
    private List<BarberDTO> barbersList;
}
