package org.oddys.timetrackingspring.persist.dataaccess;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.ActivityRecordAccess;
import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.oddys.timetrackingspring.persist.repo.ActivityRecordRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ActivityRecordAccessImpl implements ActivityRecordAccess {
    private final ActivityRecordRepository repository;

    @Override
    public Page<ActivityRecord> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
