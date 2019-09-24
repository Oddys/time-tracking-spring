package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.service.SignInService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
public class SignInController {
    private final SignInService service;

    @PostMapping("/signin")
    public String signIn(@RequestParam String login, @RequestParam String password,
            HttpSession session) {
        UserDto userDto = service.signIn(login, password.toCharArray());
        if (userDto != null) {
            session.setAttribute("user", userDto);
            return "redirect:/cabinet";
        }
        return "redirect:/";
    }
}
