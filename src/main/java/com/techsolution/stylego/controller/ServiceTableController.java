package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.response.ServicesTableResponseDTO;
import com.techsolution.stylego.service.ServiceTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/service-table")
public class ServiceTableController {

    @Autowired
    private ServiceTableService serviceTableService;

    @GetMapping
    public ResponseEntity<List<ServicesTableResponseDTO>> getServices() {
        List<ServicesTableResponseDTO> all = serviceTableService.findAll();

        return ResponseEntity.ok(all);
    }
}
