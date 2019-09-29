package org.oddys.timetrackingspring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.service.UserActivityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@SessionAttributes({"userActivities", "targetUser"})
public class ShowUserActivitiesController {
    private final UserActivityService service;

    @GetMapping("/cabinet")
    public ModelAndView showForm() {
        return new ModelAndView("cabinet", "targetUser", new UserDto());
    }

    @GetMapping("cabinet/show-user-activities")
    public String showUserActivities(@Validated @ModelAttribute("targetUser") UserDto userDto,
            BindingResult result,  Model model) {
        List userActivities = service.findUserActivityByUserId(userDto.getUserId());
        model.addAttribute("userActivities", userActivities);
        model.addAttribute("targetUser", userDto);
        return "redirect:/cabinet/user-activities";
    }
}
