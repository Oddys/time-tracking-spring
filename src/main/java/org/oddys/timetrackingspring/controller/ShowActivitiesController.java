package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.service.ActivityService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class ShowActivitiesController {
    private final ActivityService service;

    @GetMapping("/show-activities")
    public String showActivities(@RequestParam int currentPage,
            @RequestParam int rowsPerPage, HttpSession session) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage);
        Page<ActivityDto> page = service.findAll(pageable);
        List<ActivityDto> activities = IteratorUtils.toList(page.iterator());
        session.setAttribute("activities", activities);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("rowsPerPage", rowsPerPage);
        session.setAttribute("numPages", page.getTotalPages());
        return "redirect:/activities";
    }
}
