package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.request.UserBarberSaveRequestDTO;
import com.techsolution.stylego.model.Barber;
import com.techsolution.stylego.model.BarberSavedUser;
import com.techsolution.stylego.model.BarberSavedUserId;
import com.techsolution.stylego.model.User;
import com.techsolution.stylego.repository.BarberSavedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BarberSavedUserService {

    @Autowired
    private BarberSavedUserRepository barberSavedUserRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BarberService barberService;

    public List<BarberSavedUser> findSavedBarbersByUserId(Long userId) {
        return barberSavedUserRepository.findAllById_User(userId);
    }

    @Transactional
    public boolean saveBarberByUser(UserBarberSaveRequestDTO userBarberSaveRequestDTO) {
        User user = userService.searchUserByUuid(userBarberSaveRequestDTO.getUserUuid());
        Barber barber = barberService.searchBarberByUuid(userBarberSaveRequestDTO.getBarberUuid());

        BarberSavedUserId barberSavedUserId = new BarberSavedUserId(user.getId(), barber.getId());
        boolean exists = barberSavedUserRepository.existsById(barberSavedUserId);

        if (exists) {
            return false;
        }

        BarberSavedUser barberSavedUser = new BarberSavedUser(barberSavedUserId);

        barberSavedUserRepository.save(barberSavedUser);
        return true;
    }

    @Transactional
    public void deleteBarberByUser(String userUuid, String barberUuid) {
        User user = userService.searchUserByUuid(userUuid);
        Barber barber = barberService.searchBarberByUuid(barberUuid);

       barberSavedUserRepository.deleteById_UserAndId_Barber(user.getId(), barber.getId());
    }
}
