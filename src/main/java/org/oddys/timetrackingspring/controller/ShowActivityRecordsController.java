package org.oddys.timetrackingspring.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.IteratorUtils;
import org.oddys.timetrackingspring.dto.ActivityRecordDto;
import org.oddys.timetrackingspring.dto.ActivityRecordsPageDto;
import org.oddys.timetrackingspring.dto.ActivityRecordsPageRequestDto;
import org.oddys.timetrackingspring.service.ActivityRecordService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
@SessionAttributes("activityRecordsPageDto")
public class ShowActivityRecordsController {
    private final ActivityRecordService service;

    @GetMapping("cabinet/show-activity-records")
    public String show(@RequestParam Long userActivityId,
            @RequestParam boolean userActivityAssigned,
            @RequestParam int currentPage,
            @RequestParam int rowsPerPage, Model model) {
        Page<ActivityRecordDto> page = service.findAll(currentPage, rowsPerPage);
        List<ActivityRecordDto> activityRecords = IteratorUtils.toList(page.iterator());
        ActivityRecordsPageDto dto = new ActivityRecordsPageDto(activityRecords,
                page.getTotalPages(), userActivityId, userActivityAssigned,
                currentPage, rowsPerPage);
        model.addAttribute("activityRecordsPageDto", dto);
        return "redirect:/cabinet/activity-records";
    }

    @PostMapping("cabinet/show-activity-records")
    public String show(@Validated @ModelAttribute("activityRecordsPageRequestDto")
            ActivityRecordsPageRequestDto requestPageDto,
            BindingResult result,  Model model) {
        Page<ActivityRecordDto> page = service.findAll(requestPageDto.getCurrentPage(),
                requestPageDto.getRowsPerPage());
        List<ActivityRecordDto> activityRecords = IteratorUtils.toList(page.iterator());
        model.addAttribute("activityRecordsPageDto",
                new ActivityRecordsPageDto(activityRecords, page.getTotalPages(),
                        requestPageDto));
        return "redirect:/cabinet/activity-records";
    }
}
