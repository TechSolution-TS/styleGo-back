package com.techsolution.stylego.controller;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.request.BarberRequestDTO;
import com.techsolution.stylego.dto.response.BarberResponseDTO;
import com.techsolution.stylego.dto.response.UserResponseDTO;
import com.techsolution.stylego.mapper.BarberMapper;
import com.techsolution.stylego.model.Barber;
import com.techsolution.stylego.service.BarberService;
import com.techsolution.stylego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barbers")
@RequiredArgsConstructor
public class BarberController {

    private final BarberService barberService;
    private final BarberMapper barberMapper;
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<BarberResponseDTO> registerBarber(@RequestBody BarberRequestDTO barberDTO) {
        Barber barber = barberService.registerBarber(barberDTO);
        UserResponseDTO userResponseDTO = userService.searchUser(barber.getUser().getId());

        return ResponseEntity.ok(barberMapper.barberToResponseDTO(barber, userResponseDTO));
    }

    @GetMapping("/online")
    public  ResponseEntity<List<BarberDTO>> getBarbersActive() {
        List<BarberDTO> barbers = barberService.barbersOnline();

        return ResponseEntity.ok(barbers);
    }

    @PutMapping("/{barberUuid}/online")
        public ResponseEntity<Void> ActiveBarber(@PathVariable String barberUuid) {
        barberService.ActiveBarber(barberUuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Page<BarberDTO>> getBarbers(
            @RequestParam(required = false) Integer numberCuts,
            @RequestParam(required = false) Boolean online,
            @RequestParam(required = false) Double assessment,
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {

        Page<BarberDTO> barbers = barberService.findBarbersWithFilters(numberCuts, online, assessment, pageable);
        return ResponseEntity.ok(barbers);
    }
}
