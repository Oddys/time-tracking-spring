package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.RoleDto;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.persist.Roles;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityService {  // TODO Consider moving SignIn and SignOut here
    private final Users users;
    private final Roles roles;
    private final ModelMapper modelMapper;

    public boolean addUser(UserRequestDto userDto) {
        if (users.exists(userDto.getLogin())) {
            return false;
        }
        User user = modelMapper.map(userDto, User.class);
        return users.add(user).getUserId() != null;
    }

    @Transactional(readOnly = true)
    public List<RoleDto> findAllRoles() {
        return roles.findAll().stream()
                .map(r -> modelMapper.map(r, RoleDto.class))
                .collect(Collectors.toList());
    }
}
