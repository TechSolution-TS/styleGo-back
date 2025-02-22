package com.techsolution.stylego.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDTO {
    private String name;
    private String email;
    private String uuid;
}
