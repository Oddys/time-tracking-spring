package org.oddys.timetrackingspring.persist.impl;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.Roles;
import org.oddys.timetrackingspring.persist.entity.Role;
import org.oddys.timetrackingspring.persist.repo.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class RolesImpl implements Roles {
    private final RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAllByOrderByRoleNameAsc();
    }
}
