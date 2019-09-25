package org.oddys.timetrackingspring.util;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.persist.entity.Role;
import org.oddys.timetrackingspring.persist.entity.User;

import javax.servlet.http.HttpServletRequest;

@AllArgsConstructor
public class EntityMapper {
    private final PasswordManager passwordManager;

    public User mapUser(HttpServletRequest req) {
        Long roleId = "ADMIN".equals(req.getParameter("role").toUpperCase()) ? 1L : 2L;  // FIXME
        Role role = new Role(roleId, req.getParameter("role").toUpperCase());
//        Role role = new Role(null, req.getParameter("role").toUpperCase());
        return new User(null,
                req.getParameter("login"),
                passwordManager.hashPassword(req.getParameter("password")),
                req.getParameter("firstName"),
                req.getParameter("lastName"),
                role);
    }
}
