package org.oddys.timetrackingspring.controller;

import org.oddys.timetrackingspring.dto.ActivityRecordsPageRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserActivitiesController {
    @GetMapping("/cabinet/user-activities")
    public String showForm(Model model) {
        model.addAttribute("activityRecordsPageRequestDto",
                new ActivityRecordsPageRequestDto());
        return "/cabinet/user-activities";
    }
}
