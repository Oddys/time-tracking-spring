package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.User;

public interface Users {
    User findByLogin(String login);

    boolean exists(String login);

    boolean add(User user);
}
