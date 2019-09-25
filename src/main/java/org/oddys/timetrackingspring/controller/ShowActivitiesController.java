package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.service.ActivityService;
import org.oddys.timetrackingspring.util.AttributeSetter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    private final AttributeSetter attributeSetter;

    @GetMapping("/show-activities")
    public String showActivities(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int rowsPerPage, HttpSession session) {
        Page<ActivityDto> page = service.findAll(currentPage, rowsPerPage);
        List<ActivityDto> activities = IteratorUtils.toList(page.iterator());
        attributeSetter.setShowActivities(session, activities, currentPage,
                rowsPerPage, page.getTotalPages());
        return "redirect:/activities";
    }
}
