package com.techsolution.stylego.mapper;

import com.techsolution.stylego.dto.response.ServiceRequestsResponseDTO;
import com.techsolution.stylego.dto.response.ServicesTableResponseDTO;
import com.techsolution.stylego.model.RequestedServices;
import com.techsolution.stylego.model.ServiceRequests;
import com.techsolution.stylego.model.ServicesTable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestedServicesMapper {

    public ServiceRequestsResponseDTO modelToDto(ServiceRequests serviceRequest, List<RequestedServices> requestedServices) {
        List<ServicesTableResponseDTO> services = addListModelToDto(requestedServices);


        return ServiceRequestsResponseDTO.builder()
                .userUuid(serviceRequest.getUserUuid())
                .requestDate(serviceRequest.getRequestDate())
                .totalPrice(serviceRequest.getTotalPrice())
                .rate(7.0)
                .services(services)
                .build();
    }

    public List<ServicesTableResponseDTO> addListModelToDto(List<RequestedServices> requestedServices) {
        return requestedServices.stream()
                .map(requested -> ServicesTableResponseDTO.builder()
                        .name(requested.getService().getName())
                        .serviceType(requested.getService().getServiceType())
                        .price(requested.getService().getPrice())
                        .durationMinutes(requested.getService().getDurationMinutes())
                        .serviceUuid(requested.getService().getServiceUuid())
                        .build())
                .collect(Collectors.toList());
    }

}
