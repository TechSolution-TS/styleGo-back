package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.request.BarberRequestDTO;
import com.techsolution.stylego.exception.UserNotFoundException;
import com.techsolution.stylego.mapper.BarberMapper;
import com.techsolution.stylego.model.Barber;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BarberService {
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BarberMapper barberMapper;

    public Barber registerBarber(BarberRequestDTO barberDTO) {
        User user = userService.registerUser(barberDTO.getUser());
        Barber barber = barberMapper.dtoToBarber(user);

        return barberRepository.save(barber);
    }

    public List<BarberDTO> findBarbersByIds(List<Long> barberIds) {
        List<Barber> barbers = barberRepository.findAllById(barberIds);
        return barbers.stream().map(barberMapper::barberUserToDTO).toList();
    }

    public List<BarberDTO> barbersOnline() {
        List<Barber> onlineBarbers = barberRepository.findAllByOnlineTrue();

        return onlineBarbers.stream()
                .map(barberMapper::barberUserToDTO)
                .toList();
    }

    public void ActiveBarber(String barberUuid) {
        Barber barber = searchBarberByUuid(barberUuid);
        barberRepository.updateOnlineStatusToTrue(barber.getId());
    }

    @Transactional
    @Scheduled(fixedRateString = "${scheduler.deactivate-time}")
    public void deactivateAllBarbers() {
        barberRepository.setAllOffline();
        System.out.println("Todos os barbeiros foram definidos como offline às " + new java.util.Date());
    }

    public Page<BarberDTO> findBarbersWithFilters(Integer numberCuts, Boolean online, Double assessment, Pageable pageable) {
        Page<Barber> barbers = barberRepository.findByFilters(numberCuts, online, assessment, pageable);
        return barbers.map(barberMapper::barberUserToDTO);
    }

    public Barber searchBarberByUuid(String barberUuid) {
        return barberRepository.findByUserUuid(barberUuid)
                .orElseThrow(() -> new UserNotFoundException("Barber with UUID " + barberUuid + " not found"));
    }
}

