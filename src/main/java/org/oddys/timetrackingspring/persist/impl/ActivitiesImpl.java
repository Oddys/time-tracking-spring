package org.oddys.timetrackingspring.persist.impl;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.Activities;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.oddys.timetrackingspring.persist.repo.ActivityRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ActivitiesImpl implements Activities {
    private final ActivityRepository repository;

    @Override
    public Activity findByName(String name) {
        return repository.findActivityByActivityNameEquals(name);
    }

    @Override
    public Page<Activity> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public boolean exists(String name) {
        return repository.existsByActivityNameEquals(name);
    }

    @Override
    public Activity add(String name) {
        return repository.save(new Activity(name));
    }
}
