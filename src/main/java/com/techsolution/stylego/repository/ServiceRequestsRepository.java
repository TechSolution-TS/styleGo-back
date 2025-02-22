package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.ServiceRequests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRequestsRepository extends JpaRepository<ServiceRequests, Long> {

    Optional<ServiceRequests> findByUserUuid(String userUuid);
    List<ServiceRequests> findByBarberUuid(String barberUuid);
}
