package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.RoleDto;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.persist.Roles;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that provides services dealing with security of the application.
 */
@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityService {
    private final Users users;
    private final Roles roles;
    private final PasswordManager passwordManager;
    private final ModelMapper modelMapper;

    /**
     * Register a new User.
     *
     * @param userDto a data transfer object from which data needed
     *                for creating a new user should be extracted
     * @return true if a new user was created, false otherwise
     */
    public boolean addUser(UserRequestDto userDto) {
        if (users.exists(userDto.getLogin())) {
            return false;
        }
        User user = modelMapper.map(userDto, User.class);
        return users.add(user).getUserId() != null;
    }

    /**
     * Retrieves a list of all Roles that can be assigned to a user.
     *
     * @return a list of RoleDtos
     */
    @Transactional(readOnly = true)
    public List<RoleDto> findAllRoles() {
        return roles.findAll().stream()
                .map(r -> modelMapper.map(r, RoleDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Performs the authentication using given credentials
     *
     * @param login a login of user to be authenticated
     * @param password a password
     * @return true if these credentials are valid, false otherwise
     */
    public UserDto signIn(String login, char[] password) {
        User user = users.findByLogin(login);
        UserDto userDto = null;
        if (passwordManager.checkCredentials(login, password, user)) {
            userDto = modelMapper.map(user, UserDto.class);
        }
        passwordManager.invalidate(password);
        return userDto;
    }
}
