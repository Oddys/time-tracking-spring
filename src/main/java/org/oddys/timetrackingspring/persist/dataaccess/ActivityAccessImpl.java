package org.oddys.timetrackingspring.persist.dataaccess;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.ActivityAccess;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.oddys.timetrackingspring.persist.repo.ActivityRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ActivityAccessImpl implements ActivityAccess {
    private final ActivityRepository repository;

    @Override
    public Activity findByName(String name) {
        return repository.findActivityByActivityNameEquals(name);
    }

    @Override
    public Long countRows() {
        return repository.count();
    }

    @Override
    public List<Activity> findAll() {
        return repository.findAll(Sort.by("activity_name"));
    }
}
