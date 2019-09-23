package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.Activity;

import java.util.List;

public interface ActivityAccess {
    Activity findByName(String name);

    Long countRows();

    List<Activity> findAll();
}
