package com.techsolution.stylego.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "barbers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numberCuts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "barber_type", nullable = false)
    private Integer type;

    @Column(nullable = false)
    private Boolean online;

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BarberAssessment> barberAssessments;
}
