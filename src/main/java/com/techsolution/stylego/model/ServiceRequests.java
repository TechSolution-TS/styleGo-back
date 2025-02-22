package com.techsolution.stylego.model;

import com.techsolution.stylego.dto.request.ServiceRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "service_requests")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceRequests {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_uuid", nullable = false)
    private String userUuid;

    @Column(name = "barber_uuid", nullable = false)
    private String  barberUuid;

    @Column(name = "request_date", nullable = false)
    private Timestamp requestDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    public ServiceRequests(ServiceRequestDTO serviceRequestDTO) {
        this.barberUuid = serviceRequestDTO.getBarberUuid();
        this.userUuid = serviceRequestDTO.getUserUuid();
        this.requestDate = serviceRequestDTO.getRequestDate();
        this.totalPrice = serviceRequestDTO.getTotalPrice();
    }
}
