package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.request.ServiceRequestDTO;
import com.techsolution.stylego.exception.ServiceNotFoundException;
import com.techsolution.stylego.exception.UserNotFoundException;
import com.techsolution.stylego.model.ServiceRequests;
import com.techsolution.stylego.repository.ServiceRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceRequestsService {

    private final ServiceRequestsRepository serviceRequestsRepository;

    public ServiceRequests createRequest(ServiceRequestDTO serviceRequestDTO) {
        serviceRequestDTO.setTotalPrice(serviceRequestDTO.getTotalPrice() + 7.0);
        ServiceRequests serviceRequests = new ServiceRequests(serviceRequestDTO);

        return serviceRequestsRepository.save(serviceRequests);
    }

    public ServiceRequests findByUserUuid(String userUuid) {
        return serviceRequestsRepository.findByUserUuid(userUuid)
                .orElseThrow(() -> new ServiceNotFoundException("Service Not found"));
    }

    public Boolean existServiceRequest(String userUuid) {
        return serviceRequestsRepository.findByUserUuid(userUuid).isPresent();
    }

    public ServiceRequests findByUserUuidVerifyExistence(String userUuid) {
         if(serviceRequestsRepository.findByUserUuid(userUuid).isPresent()) {
             return serviceRequestsRepository.findByUserUuid(userUuid).get();
         }

         return new ServiceRequests();
    }

    public List<ServiceRequests> findByBarberUuid(String barberUuid) {
        return serviceRequestsRepository.findByBarberUuid(barberUuid);
    }
}
