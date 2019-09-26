package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.UserActivity;

import java.util.List;

public interface UserActivityAccessor {
    List<UserActivity> findAllByUserId(Long userId);
}
