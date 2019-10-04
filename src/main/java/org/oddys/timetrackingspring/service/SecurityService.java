package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.RoleDto;
import org.oddys.timetrackingspring.persist.Roles;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityService {  // TODO Consider moving SignIn and SignOut here
    private Users users;
    private Roles roles;
    private final PasswordManager passwordManager;
    private final ModelMapper modelMapper;

    public boolean addUser(User user) {
        if (users.exists(user.getLogin())) {
            return false;
        }
        return users.add(user);
    }

    @Transactional(readOnly = true)
    public List<RoleDto> findAllRoles() {
        return roles.findAll().stream()
                .map(r -> modelMapper.map(r, RoleDto.class))
                .collect(Collectors.toList());
    }
}
