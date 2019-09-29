package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ActivityRecords {
    Page<ActivityRecord> findAll(Pageable pageable);

    boolean exists(LocalDate date, Long userActivityId);

    void addActivityRecord(LocalDate date, Long duration, Long userActivityId);
}
