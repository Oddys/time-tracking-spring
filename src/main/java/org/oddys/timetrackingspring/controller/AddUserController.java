package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.service.SecurityService;
import org.oddys.timetrackingspring.util.EntityMapper;
import org.oddys.timetrackingspring.util.ParameterValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class AddUserController {
    private final SecurityService service;
    private final ParameterValidator validator;
    private final EntityMapper entityMapper;

    @PostMapping("/cabinet/add-user")
    public String addUser(HttpServletRequest request) {
        if (!validator.isValidAddUser(request)) {  // FIXME Make to work with a session
            return "redirect:/user-data";
        }
        String messageKey = service.addUser(entityMapper.mapUser(request))  // FIXME to user a property
                ? "Added successfully"
                : "User already exists";
        request.getSession().setAttribute("messageKey", messageKey);
        return "redirect:/cabinet/user-data";
    }
}
