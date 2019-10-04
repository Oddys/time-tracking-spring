package org.oddys.timetrackingspring.persist.impl;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.Users;
import org.oddys.timetrackingspring.persist.entity.User;
import org.oddys.timetrackingspring.persist.repo.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UsersImpl implements Users {
    private final UserRepository repository;

    @Override
    public User findByLogin(String login) {
        return repository.findUserByLoginEquals(login);
    }

    @Override
    public boolean exists(String login) {
        return repository.existsByLogin(login);
    }

    @Override
    public User add(User user) {
        return repository.save(user);
    }
}
