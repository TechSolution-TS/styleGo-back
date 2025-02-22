package com.techsolution.stylego.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "requested_services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RequestedServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private ServiceRequests request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServicesTable service;

    public RequestedServices(ServiceRequests request, ServicesTable service) {
        this.request = request;
        this.service = service;
    }
}
