package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityRecordAccess {
    Page<ActivityRecord> findAll(Pageable pageable);
}
