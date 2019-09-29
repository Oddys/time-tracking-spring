package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRecordRepository extends JpaRepository<ActivityRecord, Long> {
    Page<ActivityRecord> findAll(Pageable pageable);
}
