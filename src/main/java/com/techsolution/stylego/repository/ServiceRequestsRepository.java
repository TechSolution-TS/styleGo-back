package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.ServiceRequests;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRequestsRepository extends JpaRepository<ServiceRequests, Long> {

    Page<ServiceRequests> findByUserUuid(String userUuid, Pageable pageable);
    List<ServiceRequests> findByUserUuidAndRequestStatus(String userUuid, String status);
    List<ServiceRequests> findByBarberUuid(String barberUuid);
}
