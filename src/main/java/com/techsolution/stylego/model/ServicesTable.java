package com.techsolution.stylego.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "table_services")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServicesTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "service_uuid")
    private String serviceUuid;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, name = "service_type")
    private Long serviceType;

    @Column(nullable = false, name = "duration_minutes")
    private Long durationMinutes;

    @Column(nullable = false, name = "price")
    private Double price;
}
