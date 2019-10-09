package org.oddys.timetrackingspring.util;

import lombok.NoArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.oddys.timetrackingspring.persist.entity.User;

import java.util.Arrays;
import java.util.Objects;

/**
 * Utility class for managing passwords.
 */
@NoArgsConstructor
public class PasswordManager {
    /**
     * Provides a hash for a given String.
     *
     * @param password a password String
     * @return a hashed String
     *
     * @see DigestUtils
     */
    public String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    /**
     * Fills a given array with meaningless characters.
     *
     * @param password a password represented as an array of chars
     */
    public void invalidate(char[] password) {
        Arrays.fill(password, Character.MIN_VALUE);
    }

    /**
     * Checks whether a given login and password match those of the user.
     *
     * @param login a login
     * @param password a password represented as an array of chars
     * @param user User object
     * @return true if these login and password match those of this User, false otherwise
     */
    public boolean checkCredentials(String login, char[] password, User user) {
        return user != null && Objects.equals(login, user.getLogin())
                && user.getPassword().equals(hashPassword(String.valueOf(password)));
    }
}
