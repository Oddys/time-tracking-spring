package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findActivityByActivityNameEquals(String name);
}
