package com.techsolution.stylego.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String name;
    private String imageUrl;
    private String email;
    private String password;
    private String address;
    private String city;
    private String complement;
    private String neighborhood;
    private String number;
    private String state;
    private String zipCode;
    private String country;
    private String uuid;
}
