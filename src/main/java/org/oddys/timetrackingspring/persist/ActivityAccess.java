package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActivityAccess {
    Activity findByName(String name);

    Page<Activity> findAll(Pageable pageable);
}
