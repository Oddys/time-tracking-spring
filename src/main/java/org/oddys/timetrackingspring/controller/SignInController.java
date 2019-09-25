package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.service.SignInService;
import org.oddys.timetrackingspring.util.ParameterValidator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class SignInController {
    private final SignInService service;
    private final ParameterValidator validator;

    @PostMapping("/signin")
    public String signIn(HttpServletRequest request) {
        if (!validator.isValidSignIn(request)) {
            return "redirect:/";
        }
        UserDto userDto = service.signIn(request.getParameter("login"),
                request.getParameter("password").toCharArray());
        if (userDto != null) {
            request.getSession().setAttribute("user", userDto);
            return "redirect:/cabinet";
        }
        request.getSession().setAttribute("messageKey", "auth.error.notfound");
        return "redirect:/";
    }
}
