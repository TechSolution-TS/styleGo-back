package com.techsolution.stylego.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Embeddable
public class BarberSavedUserId implements Serializable {

    @Column(name = "user_id")
    private Long user;

    @Column(name = "barber_id")
    private Long barber;
}
