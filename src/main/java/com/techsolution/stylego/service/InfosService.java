package com.techsolution.stylego.service;

import com.techsolution.stylego.dto.BarberDTO;
import com.techsolution.stylego.dto.UserDTO;
import com.techsolution.stylego.dto.response.UserInfosResponseDTO;
import com.techsolution.stylego.mapper.UserMapper;
import com.techsolution.stylego.model.BarberSavedUser;
import com.techsolution.stylego.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfosService {

    @Autowired
    private UserService userService;
    @Autowired
    private BarberService barberService;
    @Autowired
    private BarberSavedUserService barberSavedUserService;
    @Autowired
    private ServiceTableService serviceTableService;
    @Autowired
    private UserMapper userMapper;

    public UserInfosResponseDTO searchUserAndParameters(String uuid) {
        User user = userService.searchUserByUuid(uuid);
        List<BarberDTO> barbers = getSavedBarbers(user.getId());
        UserDTO userDTO = userMapper.userToDto(user);

        return new UserInfosResponseDTO(userDTO, barbers);
    }

    private List<BarberDTO> getSavedBarbers(Long userId) {
        List<BarberSavedUser> savedUsers = barberSavedUserService.findSavedBarbersByUserId(userId);
        List<Long> barberIds = savedUsers.stream()
                .map(register -> register.getId().getBarber())
                .toList();

        return barberService.findBarbersByIds(barberIds);
    }
}
