package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserRequestDto;
import org.oddys.timetrackingspring.service.SecurityService;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class AddUserController {
    private final SecurityService service;
    private final ModelMapper modelMapper;
    private final PasswordManager passwordManager;

    @GetMapping("/cabinet/user-data")
    public String showUserForm(Model model) {
        model.addAttribute("roles", service.findAllRoles());
        if (!model.containsAttribute("userDto")) {
            model.addAttribute("userDto", new UserRequestDto());
        }
        return "/cabinet/user-data";
    }

    @PostMapping("/cabinet/add-user")
    public String addUser(@Valid @ModelAttribute("userDto") UserRequestDto userDto,
            BindingResult result,
            RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userDto", result);
            attributes.addFlashAttribute("userDto", userDto);
            return "redirect:/cabinet/user-data";
        }
        userDto.setPassword(passwordManager.hashPassword(userDto.getPassword()));
        String message = service.addUser(userDto)
                ? "OK"
                : "NOT OK";
        attributes.addFlashAttribute("message", message);
        return "redirect:/cabinet/user-data";
    }
}
