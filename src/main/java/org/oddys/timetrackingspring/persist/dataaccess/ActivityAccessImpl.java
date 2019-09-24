package org.oddys.timetrackingspring.persist.dataaccess;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.ActivityAccess;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.oddys.timetrackingspring.persist.repo.ActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ActivityAccessImpl implements ActivityAccess {
    private final ActivityRepository repository;

    @Override
    public Activity findByName(String name) {
        return repository.findActivityByActivityNameEquals(name);
    }

//    @Override
//    public Long countRows() {
//        return repository.count();
//    }

    @Override
    public Page<Activity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
