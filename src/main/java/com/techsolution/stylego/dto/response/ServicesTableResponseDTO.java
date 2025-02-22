package com.techsolution.stylego.dto.response;

import com.techsolution.stylego.model.ServicesTable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServicesTableResponseDTO {

    private String serviceUuid;
    private String name;
    private Long serviceType;
    private Long durationMinutes;
    private Double price;

    public ServicesTableResponseDTO(ServicesTable servicesTable) {
        this.serviceUuid = servicesTable.getServiceUuid();
        this.name = servicesTable.getName();
        this.serviceType = servicesTable.getServiceType();
        this.durationMinutes = servicesTable.getDurationMinutes();
        this.price = servicesTable.getPrice();
    }
}
