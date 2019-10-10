package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.service.SecurityService;
import org.oddys.timetrackingspring.util.BundleProvider;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Optional;
import java.util.ResourceBundle;

@Controller
@AllArgsConstructor
@Validated
@Slf4j
public class SecurityController {
    private final SecurityService service;
    private final PasswordManager passwordManager;
    private final BundleProvider bundleProvider;

    @GetMapping("/cabinet/user-data")
    public String showUserForm(Model model) {
        model.addAttribute("roles", service.findAllRoles());
        if (!model.containsAttribute("userDto")) {
            model.addAttribute("userDto", new UserRequestDto());
        }
        return "/cabinet/user-data";
    }

    @PostMapping("/cabinet/add-user")
    public String addUser(@ModelAttribute("userDto") @Valid UserRequestDto userDto,
            BindingResult result, @SessionAttribute @Nullable String lang,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
            attributes.addFlashAttribute("userDto", userDto);
            return "redirect:/cabinet/user-data";
        }
        userDto.setPassword(passwordManager.hashPassword(userDto.getPassword()));
        String messageKey = service.addUser(userDto) ? "user.add.success"
                                                     : "user.add.fail";
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        attributes.addFlashAttribute("message", bundle.getString(messageKey));
        return "redirect:/cabinet/user-data";
    }

    @PostMapping("/signin")
    public String signIn(@RequestParam @NotBlank String login,
            @RequestParam @NotBlank String password,
            @SessionAttribute @Nullable String lang,
            RedirectAttributes attributes) {
        UserDto userDto = service.signIn(login, password.toCharArray());
        if (userDto != null) {
            attributes.addFlashAttribute("user", userDto);
            log.info(String.format("%s %s (%s) signed in", userDto.getFirstName(),
                    userDto.getLastName(), userDto.getLogin()));
            return "redirect:/cabinet";
        }
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        attributes.addFlashAttribute("message",
                bundle.getString("auth.error.notfound"));
        return "redirect:/";
    }

    @GetMapping("/signout")
    public String signOut(HttpSession session) {
        String user = Optional.ofNullable((UserDto) session.getAttribute("user"))
                .map(u -> String.format("%s %s (%s)", u.getFirstName(),
                        u.getLastName(), u.getLogin()))
                .orElse("User with the expired session");
        session.invalidate();
        log.info(user + " signed out");
        return "redirect:/";
    }
}
