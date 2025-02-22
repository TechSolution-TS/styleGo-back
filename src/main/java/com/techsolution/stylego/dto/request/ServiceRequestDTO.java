package com.techsolution.stylego.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ServiceRequestDTO {

    private String barberUuid;
    private String userUuid;
    private Timestamp requestDate;
    private Double totalPrice;
    private List<String> services;
}
