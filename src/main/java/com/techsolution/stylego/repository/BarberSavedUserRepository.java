package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.BarberSavedUser;
import com.techsolution.stylego.model.BarberSavedUserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarberSavedUserRepository extends JpaRepository<BarberSavedUser, BarberSavedUserId> {
    List<BarberSavedUser> findAllById_User(Long userId);
    void deleteById_UserAndId_Barber(Long userId, Long barberId);
}
