package org.oddys.timetrackingspring.persist.dataaccess;

import lombok.RequiredArgsConstructor;
import org.oddys.timetrackingspring.persist.UserActivityAccessor;
import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.oddys.timetrackingspring.persist.repo.UserActivityRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserActivityAccessorImpl implements UserActivityAccessor {
    private final UserActivityRepository repository;

    @Override
    public List<UserActivity> findAllByUserId(Long userId) {
        return repository.findAllByUserUserId(userId);
    }
}
