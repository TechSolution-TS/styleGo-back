package com.techsolution.stylego.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "barber_saved_users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BarberSavedUser {

    @EmbeddedId
    private BarberSavedUserId id;
}
