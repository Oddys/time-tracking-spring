package org.oddys.timetrackingspring.persist;

import org.oddys.timetrackingspring.persist.entity.Role;

import java.util.List;

public interface Roles {
    List<Role> findAll();
}
