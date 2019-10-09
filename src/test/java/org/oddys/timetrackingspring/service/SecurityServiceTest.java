package org.oddys.timetrackingspring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.persist.Roles;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.util.PasswordManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SecurityServiceTest {
    @Mock
    private Users users;

    @Mock
    private Roles roles;

    @Mock
    private PasswordManager passwordManager;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SecurityService service;

    @Test
    public void testSignIn_validCredentials() {
        String login = "login";
        char[] password = "password".toCharArray();
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordManager.hashPassword(String.valueOf(password)));
        when(users.findByLogin(any(String.class))).thenReturn(user);
        when(passwordManager.checkCredentials(login, password, user)).thenReturn(true);
        service.signIn(login, password);
        verify(modelMapper).map(user, UserDto.class);
        verify(passwordManager).invalidate(password);
    }

    @Test
    public void testSignIn_userDoesNotExist() {
        String login = "login";
        char[] password = "password".toCharArray();
        when(users.findByLogin(any(String.class))).thenReturn(null);
        assertNull(service.signIn(login, password));
        verify(passwordManager).invalidate(password);
    }

    @Test
    public void testAddUser_userExists() {
        when(users.exists(any(String.class))).thenReturn(true);
        UserRequestDto dto = new UserRequestDto();
        dto.setLogin("login");
        assertFalse(service.addUser(dto));
    }

    @Test
    public void testAddUser_userDoesNotExists() {
        when(users.exists(any(String.class))).thenReturn(false);
        User user = new User();
        user.setUserId(1L);
        when(modelMapper.map(any(UserRequestDto.class), eq(User.class))).thenReturn(user);
        when(users.add(any(User.class))).thenReturn(user);
        UserRequestDto dto = new UserRequestDto();
        dto.setLogin("login");
        service.addUser(dto);
        verify(modelMapper).map(dto, User.class);
        verify(users).add(any(User.class));
    }
}
