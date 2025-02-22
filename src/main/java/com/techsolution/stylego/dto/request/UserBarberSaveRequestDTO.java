package com.techsolution.stylego.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserBarberSaveRequestDTO {
    private String barberUuid;
    private String userUuid;
}
