package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.response.ServicesTableResponseDTO;
import com.techsolution.stylego.exception.ServiceNotFoundException;
import com.techsolution.stylego.model.ServicesTable;
import com.techsolution.stylego.repository.ServiceTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceTableService {

    @Autowired
    private ServiceTableRepository serviceTableRepository;

    public List<ServicesTableResponseDTO> findAll() {
        List<ServicesTable> services = serviceTableRepository.findAll();

        return services.stream()
                .map(service -> new ServicesTableResponseDTO(service))
                .toList();
    }

    public ServicesTable findServiceByUuid(String serviceUuid) {
       return serviceTableRepository.findByServiceUuid(serviceUuid)
                .orElseThrow(() -> new ServiceNotFoundException("Service with UUID " + serviceUuid + " not found"));
    }
}
