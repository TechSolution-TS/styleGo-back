package com.techsolution.stylego.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
public class ServiceRequestsResponseDTO {

    private String userUuid;
    private Timestamp requestDate;
    private Double totalPrice;
    private Double rate;
    private List<ServicesTableResponseDTO> services;
}
