package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.oddys.timetrackingspring.dto.ActivitiesPageDto;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@SessionAttributes("activitiesPage")
public class ShowActivitiesController {
    private final ActivityService service;

    @GetMapping("/show-activities")
    public String showActivities(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int rowsPerPage, Model model) {
        Page<ActivityDto> page = service.findAll(currentPage, rowsPerPage);
        List<ActivityDto> activities = IteratorUtils.toList(page.iterator());
        model.addAttribute("activitiesPage", new ActivitiesPageDto(
                activities, currentPage, rowsPerPage, page.getTotalPages()));
        return "redirect:/activities";
    }
}
