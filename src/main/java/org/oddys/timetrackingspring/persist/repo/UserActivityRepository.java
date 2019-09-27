package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findAllByUserUserId(Long id);
}