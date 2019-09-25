package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLoginEquals(String login);

    boolean existsByLogin(String login);

    @SuppressWarnings("unchecked")
    User save(User user);
}
