package org.oddys.timetrackingspring.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes("messageKey")
public class AddActivityRecordController {

    @PostMapping("/cabinet/add-activity-record")
    public String add(@RequestParam Long userActivityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long duration, Model model) {
        return "redirect:/cabinet/activity-records";
    }
}
