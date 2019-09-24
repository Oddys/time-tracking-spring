package org.oddys.timetrackingspring.util;

import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.oddys.timetrackingspring.persist.entity.User;

import java.util.Arrays;
import java.util.Objects;

@NoArgsConstructor
public class PasswordManager {
    public String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public void invalidate(char[] password) {
        Arrays.fill(password, Character.MIN_VALUE);
    }

    public boolean checkCredentials(String login, char[] password, User user) {
        return user != null && Objects.equals(login, user.getLogin())
                && user.getPassword().equals(hashPassword(String.valueOf(password)));
    }
}
