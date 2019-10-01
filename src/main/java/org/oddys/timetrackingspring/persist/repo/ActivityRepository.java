package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findActivityByActivityNameEquals(String name);

    Page<Activity> findAll(Pageable pageable);

    boolean existsByActivityNameEquals(String name);

    @SuppressWarnings("unchecked")
    Activity save(Activity activity);
}
