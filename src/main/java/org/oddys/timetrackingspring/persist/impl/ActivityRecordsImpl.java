package org.oddys.timetrackingspring.persist.impl;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.ActivityRecords;
import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.oddys.timetrackingspring.persist.repo.ActivityRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@AllArgsConstructor
public class ActivityRecordsImpl implements ActivityRecords {
    private final ActivityRecordRepository repository;

    @Override
    public Page<ActivityRecord> findAll(Long userActivityId, Pageable pageable) {
        return repository.findAllByUserActivityUserActivityId(userActivityId, pageable);
    }

    @Override
    public boolean exists(LocalDate date, Long userActivityId) {
        return repository.existsByActivityDateAndUserActivityUserActivityId(date,
                userActivityId);
    }

    @Override
    public void addActivityRecord(LocalDate date, Long duration, Long userActivityId) {
        repository.addActivityRecord(date, duration, userActivityId);
    }
}
