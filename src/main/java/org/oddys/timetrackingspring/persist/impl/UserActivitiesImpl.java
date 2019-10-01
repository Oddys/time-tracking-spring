package org.oddys.timetrackingspring.persist.impl;

import lombok.RequiredArgsConstructor;
import org.oddys.timetrackingspring.persist.UserActivities;
import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.oddys.timetrackingspring.persist.repo.UserActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserActivitiesImpl implements UserActivities {
    private final UserActivityRepository repository;

    @Override
    public List<UserActivity> findAllByUserId(Long userId) {
        return repository.findAllByUserUserId(userId);
    }

    @Override
    public Page<UserActivity> findRequestsForStatusChange(Pageable pageable) {
        return repository.findAllByStatusChangeRequestedIsTrue(pageable);
    }

    @Override
    public void updateAssignedAndStatusChangeRequested(Long userActivityId,
            Boolean assigned) {
        repository.updateAssignedAndStatusChangeRequested(userActivityId,
                assigned);
    }
}
