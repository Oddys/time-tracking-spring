package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserActivityRepository extends JpaRepository<UserActivity, Long> {
    List<UserActivity> findAllByUserUserId(Long id);

    Page<UserActivity> findAllByStatusChangeRequestedIsTrue(Pageable pageable);

    @SuppressWarnings("unchecked")
    UserActivity save(UserActivity userActivity);

    @Modifying
    @Query(value = "UPDATE user_activities SET assigned = :assigned, status_change_requested = false WHERE user_activity_id = :user_activity_id",
        nativeQuery = true)
    void updateAssignedAndStatusChangeRequested(@Param("user_activity_id") Long userActivityId,
            @Param("assigned") Boolean assigned);

    boolean existsByUserUserIdAndActivity_ActivityId(Long userId, Long activityId);

    @Modifying
    @Query(value = "UPDATE user_activities SET status_change_requested = true WHERE user_activity_id = :user_activity_id",
            nativeQuery = true)
    int requestStatusChange(@Param("user_activity_id") Long userActivityId);
}
