package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import org.oddys.timetrackingspring.dto.ActivityRecordsPageDto;
import org.oddys.timetrackingspring.service.ActivityRecordService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;

@Controller
@SessionAttributes({"messageKey", "activityRecordsPageDto"})
@AllArgsConstructor
public class AddActivityRecordController {
    private final int STARTING_PAGE = 0;
    private final ActivityRecordService service;

    @PostMapping("/cabinet/add-activity-record")
    public String add(@RequestParam Long userActivityId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam Long duration,
            @SessionAttribute("activityRecordsPageDto") ActivityRecordsPageDto dto,
            Model model) {
        String messageKey = service.addActivityRecord(date, duration, userActivityId)
                ? "activity.record.add.success"
                : "activity.record.add.fail";
        model.addAttribute("messageKey", messageKey);
        return String.format("redirect:/cabinet/show-activity-records?userActivityAssigned=%1b&userActivityId=%2d&rowsPerPage=%3d&currentPage=%4d",
                dto.getUserActivityAssigned(),
                dto.getUserActivityId(),
                dto.getRowsPerPage(),
                STARTING_PAGE);
    }
}
