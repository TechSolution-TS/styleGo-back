package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.RequestedServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestedServicesRepository extends JpaRepository<RequestedServices, Long> {
    List<RequestedServices> findByRequestId(Long id);
}
