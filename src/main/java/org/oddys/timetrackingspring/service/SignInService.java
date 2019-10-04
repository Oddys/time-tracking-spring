package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SignInService {
    private final Users users;
    private final PasswordManager passwordManager;
    private final ModelMapper modelMapper;

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
