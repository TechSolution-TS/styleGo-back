package com.techsolution.stylego.repository;

import com.techsolution.stylego.model.ServicesTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceTableRepository extends JpaRepository<ServicesTable, Long> {
    Optional<ServicesTable> findByServiceUuid(String serviceUuid);
}
