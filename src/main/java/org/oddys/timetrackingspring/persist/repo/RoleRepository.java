package org.oddys.timetrackingspring.persist.repo;

import org.oddys.timetrackingspring.persist.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findAllByOrderByRoleNameAsc();
}
