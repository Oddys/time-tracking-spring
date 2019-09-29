package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ActivityRecordRepository extends JpaRepository<ActivityRecord, Long> {
    Page<ActivityRecord> findAllByUserActivityUserActivityId(Long id, Pageable pageable);

    boolean existsByActivityDateAndUserActivityUserActivityId(LocalDate date, Long userActivityId);

    @Modifying
    @Query(value = "INSERT INTO activity_records (activity_date, duration, user_activity_id) VALUES (:recordDate, :duration, :userActivityId)",
           nativeQuery = true)
    void addActivityRecord(@Param("recordDate") LocalDate date,
            @Param("duration") Long duration,
            @Param("userActivityId") Long userActivityId);
}
