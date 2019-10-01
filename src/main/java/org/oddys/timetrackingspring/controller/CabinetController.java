package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.hibernate.validator.constraints.Range;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.dto.PageDto;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.service.AdminService;
import org.oddys.timetrackingspring.service.CommonService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Controller
@RequestMapping("/cabinet")
@AllArgsConstructor
@Validated
@SessionAttributes({"messageKey", "activityName"})
public class CabinetController {
    private final int FIRST_PAGE = 0;
    private final int ROWS_PER_PAGE = 5;
    private final AdminService adminService;
    private final CommonService commonService;

    @GetMapping("/user-activity-requests")
    public String showActivityRequests(@RequestParam @Min(value=0) int currentPage,
            @RequestParam @Range(min=5, max=50) int rowsPerPage, Model model) {
        Page<UserActivityDto> page = adminService.showRequestsForStatusChange(
                currentPage, rowsPerPage);
        PageDto<UserActivityDto> pageDto = new PageDto<>(
                IteratorUtils.toList(page.iterator()),
                currentPage,
                rowsPerPage,
                page.getTotalPages()
        );
        model.addAttribute("pageDto", pageDto);
        return "cabinet/user-activity-requests";
    }

    @PostMapping("/change-user-activity-status")
    public String changeUserActivityStatus(@RequestParam @Min(value=1) Long userActivityId,
            @RequestParam Boolean currentAssigned, Model model) {
        adminService.changeUserActivityStatus(userActivityId, currentAssigned);
        model.addAttribute("messageKey", "user.activity.status.changed");
        return String.format("redirect:/cabinet/user-activity-requests?currentPage=%d&rowsPerPage=%d",
            FIRST_PAGE, ROWS_PER_PAGE);
    }

    @GetMapping("/activities")
    public String showActivities(@RequestParam(defaultValue = "0") int currentPage,
            @RequestParam(defaultValue = "5") int rowsPerPage, Model model) {
        Page<ActivityDto> page = commonService.findAll(currentPage, rowsPerPage);
        PageDto<ActivityDto> pageDto = new PageDto<>(
                IteratorUtils.toList(page.iterator()),
                currentPage,
                rowsPerPage,
                page.getTotalPages()
        );
        model.addAttribute("pageDto", pageDto);
        return "cabinet/activities";
    }

    @PostMapping("/add-activity")
    public String addActivity(@RequestParam @NotBlank String activityName, Model model) {
        String messageKey = adminService.addActivity(activityName)
                ? "activity.add.success"
                : "activity.add.fail";
        model.addAttribute("messageKey", messageKey);
        model.addAttribute("activityName", activityName);
        return String.format("redirect:/cabinet/activities?currentPage=%d&rowsPerPage=%d",
                FIRST_PAGE, ROWS_PER_PAGE);
    }
}
