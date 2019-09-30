package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserActivities {
    List<UserActivity> findAllByUserId(Long userId);

    Page<UserActivity> findRequestsForStatusChange(Pageable pageable);
}
