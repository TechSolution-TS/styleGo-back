package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.request.ServiceRequestDTO;
import com.techsolution.stylego.dto.response.ServiceRequestsResponseDTO;
import com.techsolution.stylego.mapper.RequestedServicesMapper;
import com.techsolution.stylego.model.RequestedServices;
import com.techsolution.stylego.model.ServiceRequests;
import com.techsolution.stylego.model.ServicesTable;
import com.techsolution.stylego.repository.RequestedServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestedServicesService {

    private final RequestedServicesRepository requestedServicesRepository;
    private final ServiceRequestsService serviceRequestsService;
    private final ServiceTableService serviceTableService;
    private final RequestedServicesMapper requestedServicesMapper;

    @Transactional
    public Boolean createServiceRequest(ServiceRequestDTO serviceRequestDTO) {
        if(!serviceRequestsService.existServiceRequest(serviceRequestDTO.getUserUuid())) {
            return false;
        }

        ServiceRequests request = serviceRequestsService.createRequest(serviceRequestDTO);
        serviceRequestDTO.getServices()
                .forEach(serviceUuid -> this.addServiceRequest(serviceUuid, request));

        return true;
    }

    public void addServiceRequest(String serviceUuid, ServiceRequests request) {
        ServicesTable service = serviceTableService.findServiceByUuid(serviceUuid);
        RequestedServices requestedServices = new RequestedServices(request, service);

        requestedServicesRepository.save(requestedServices);
    }

    public List<ServiceRequestsResponseDTO> searchRequestByBarberUuid(String barberUuid) {
        List<ServiceRequestsResponseDTO> listServiceRequestsResponseDTO = new ArrayList<>();
        List<ServiceRequests> listServiceRequest = serviceRequestsService.findByBarberUuid(barberUuid);

        listServiceRequest.forEach(serviceRequest -> {
            listServiceRequestsResponseDTO.add(addServiceRequests(serviceRequest));
        });

        return listServiceRequestsResponseDTO;
    }

    public Page<ServiceRequestsResponseDTO> searchRequestByUserUuid(String userUuid, Pageable pageable) {
        return serviceRequestsService.findByUserUuidVerifyExistence(userUuid, pageable)
                .map(serviceRequest -> addServiceRequests(serviceRequest));
    }



    private ServiceRequestsResponseDTO addServiceRequests(ServiceRequests serviceRequest) {
        List<RequestedServices> requestedServices =
                requestedServicesRepository.findByRequestId(serviceRequest.getId());

        return requestedServicesMapper.modelToDto(serviceRequest, requestedServices);
    }
}
