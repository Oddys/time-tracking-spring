package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.hibernate.validator.constraints.Range;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.dto.ActivityRecordDto;
import org.oddys.timetrackingspring.dto.ActivityRecordsPage;
import org.oddys.timetrackingspring.dto.ActivityRecordsPageRequestDto;
import org.oddys.timetrackingspring.dto.PageDto;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.dto.UserDto;
import org.oddys.timetrackingspring.service.AdminService;
import org.oddys.timetrackingspring.service.CommonService;
import org.oddys.timetrackingspring.service.UserService;
import org.oddys.timetrackingspring.util.BundleProvider;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

@Controller
@RequestMapping("/cabinet")
@AllArgsConstructor
@Validated
@SessionAttributes("user")
public class CabinetController {
    private final int FIRST_PAGE = 0;
    private final int ROWS_PER_PAGE = 5;
    private final AdminService adminService;
    private final UserService userService;
    private final CommonService commonService;
    private final BundleProvider bundleProvider;

    @GetMapping("")
    public ModelAndView showForm() {
        return new ModelAndView("cabinet", "targetUser", new UserDto());
    }

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
            @RequestParam Boolean currentAssigned, @SessionAttribute @Nullable String lang,
            RedirectAttributes attributes) {
        adminService.changeUserActivityStatus(userActivityId, currentAssigned);
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        attributes.addFlashAttribute("message",
                bundle.getString("user.activity.status.changed"));
        attributes.addAttribute("currentPage", FIRST_PAGE);
        attributes.addAttribute("rowsPerPage", ROWS_PER_PAGE);
        return "redirect:/cabinet/user-activity-requests";
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
        model.addAttribute("userActivityDto", new UserActivityDto());
        return "cabinet/activities";
    }

    @PostMapping("/add-activity")
    public String addActivity(@RequestParam @NotBlank String activityName,
            @SessionAttribute @Nullable String lang, RedirectAttributes attributes) {
        String messageKey = adminService.addActivity(activityName)
                ? "activity.add.success"
                : "activity.add.fail";
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        String message = String.format(bundle.getString(messageKey), activityName);
        attributes.addFlashAttribute("message", message);
        attributes.addAttribute("currentPage", FIRST_PAGE);
        attributes.addAttribute("rowsPerPage", ROWS_PER_PAGE);
        return "redirect:/cabinet/activities";
    }

    @PostMapping("/request-user-activity")
    public String requestUserActivity(@Valid UserActivityDto dto,
            @SessionAttribute @Nullable String lang, RedirectAttributes attributes) {
        String messageKey = userService.requestUserActivity(dto)
                ? "user.activity.request.success"
                : "user.activity.request.fail";
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        String message = String.format(bundle.getString(messageKey), dto.getActivityName());
        attributes.addFlashAttribute("message", message);
        attributes.addAttribute("currentPage", FIRST_PAGE);
        attributes.addAttribute("rowsPerPage", ROWS_PER_PAGE);
        return "redirect:/cabinet/activities";
    }

    @GetMapping("/user-activities")
    public String showUserActivities(@RequestParam @Min(1) Long userId,
            @RequestParam @NotBlank String firstName,
            @RequestParam @NotBlank String lastName,
            Model model) {
        List userActivities = userService.findUserActivityByUserId(userId);
        model.addAttribute("userActivities", userActivities);
        model.addAttribute("userId", userId);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("activityRecordsPageRequestDto",
                new ActivityRecordsPageRequestDto());
        return "/cabinet/user-activities";
    }

    @PostMapping("/stop-activity")
    public String stopUserActivity(@RequestParam Long userActivityId,
            @RequestParam @Min(1) Long userId,
            @RequestParam @NotBlank String firstName,
            @RequestParam @NotBlank String lastName,
            @SessionAttribute @Nullable String lang,
            RedirectAttributes attributes) {
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        String messageKey = userService.requestUserActivityStatusChange(userActivityId)
                ? "user.activity.stop.success"
                : "user.activity.stop.fail";
        attributes.addFlashAttribute("message", bundle.getString(messageKey));
        Map<String, String> parameters = Map.of(
                "userId", String.valueOf(userId),
                "firstName", String.valueOf(firstName),
                "lastName", String.valueOf(lastName)
        );
        attributes.addAllAttributes(parameters);
        return "redirect:/cabinet/user-activities";
    }

    @GetMapping("/activity-records")
    public String showActivityRecords(@RequestParam Long userActivityId,
            @RequestParam boolean userActivityAssigned,
            @RequestParam int currentPage,
            @RequestParam int rowsPerPage,
            Model model) {
        Page<ActivityRecordDto> page = commonService.findAll(userActivityId, currentPage, rowsPerPage);
        List<ActivityRecordDto> activityRecords = IteratorUtils.toList(page.iterator());
        ActivityRecordsPage dto = new ActivityRecordsPage(
                activityRecords,
                currentPage,
                rowsPerPage,
                page.getTotalPages(),
                userActivityId,
                userActivityAssigned
        );
        model.addAttribute("activityRecordsPage", dto);
        return "/cabinet/activity-records";
    }

    @PostMapping("/add-activity-record")
    public String add(@RequestParam Long userActivityId,
            @RequestParam Boolean userActivityAssigned,
            @RequestParam Long rowsPerPage,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long duration,
            @SessionAttribute @Nullable String lang,
            RedirectAttributes attributes) {
        ResourceBundle bundle = bundleProvider.getBundle(lang);
        String messageKey = commonService.addActivityRecord(date, duration, userActivityId)
                ? "activity.record.add.success"
                : "activity.record.add.fail";
        attributes.addFlashAttribute("message", bundle.getString(messageKey));
        Map<String, ?> parameters = Map.of(
                "userActivityAssigned", userActivityAssigned,
                "userActivityId", userActivityId,
                "rowsPerPage", rowsPerPage,
                "currentPage", FIRST_PAGE
        );
        attributes.addAllAttributes(parameters);
        return "redirect:/cabinet/activity-records";
    }
}
