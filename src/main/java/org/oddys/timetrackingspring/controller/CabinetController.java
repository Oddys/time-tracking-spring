package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.oddys.timetrackingspring.dto.PageDto;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/cabinet")
@AllArgsConstructor
@SessionAttributes("pageDto")
public class CabinetController {
    private final AdminService adminService;

    @GetMapping("/show-user-activity-requests")
    public String showActivityRequests(@RequestParam int currentPage,
            @RequestParam int rowsPerPage, Model model) {
        Page<UserActivityDto> page = adminService.showRequestsForStatusChange(
                currentPage, rowsPerPage);
        PageDto<UserActivityDto> pageDto = new PageDto<>(
                IteratorUtils.toList(page.iterator()),
                currentPage,
                rowsPerPage,
                page.getTotalPages());
        model.addAttribute("pageDto", pageDto);
        return "/cabinet/user-activity-requests";
    }
}
