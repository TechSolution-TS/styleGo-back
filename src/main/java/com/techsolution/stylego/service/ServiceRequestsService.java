package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.request.ServiceRequestDTO;
import com.techsolution.stylego.exception.ServiceNotFoundException;
import com.techsolution.stylego.exception.UserNotFoundException;
import com.techsolution.stylego.model.ServiceRequests;
import com.techsolution.stylego.model.enums.RequestStatus;
import com.techsolution.stylego.repository.ServiceRequestsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Boolean existServiceRequest(String userUuid) {
        return serviceRequestsRepository.findByUserUuidAndRequestStatus(userUuid, RequestStatus.IN_PROGRESS.getValue()).isEmpty();
    }

    public Page<ServiceRequests> findByUserUuidVerifyExistence(String userUuid, Pageable pageable) {
        return serviceRequestsRepository.findByUserUuid(userUuid, pageable);
    }

    public List<ServiceRequests> findByBarberUuid(String barberUuid) {
        return serviceRequestsRepository.findByBarberUuid(barberUuid);
    }
}
