package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.persist.UserAccess;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class SecurityService {  // TODO Consider moving SignIn and SignOut here
    private UserAccess userAccess;
    private final PasswordManager passwordManager;
    private final ModelMapper modelMapper;

    public boolean addUser(User user) {
        if (userAccess.exists(user.getLogin())) {
            return false;
        }
        return userAccess.add(user);
    }
}
