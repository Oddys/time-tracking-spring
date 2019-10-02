package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.dto.ActivityRecordDto;
import org.oddys.timetrackingspring.persist.Activities;
import org.oddys.timetrackingspring.persist.ActivityRecords;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
@Transactional
public class CommonService {
    private final Activities activities;
    private final ActivityRecords activityRecords;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public boolean activityExists(String activityName) {
        Activity activity = activities.findByName(activityName);
        return activity != null;
    }

    @Transactional(readOnly = true)
    public Page<ActivityDto> findAll(int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityName"));
        Page<Activity> page = activities.findAll(pageable);
        return page.map(a -> modelMapper.map(a, ActivityDto.class));
    }

    @Transactional(readOnly = true)
    public Page<ActivityRecordDto> findAll(Long userActivityId, int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityDate").descending());
        Page<ActivityRecord> page = activityRecords.findAll(userActivityId, pageable);
        return page.map(ar -> modelMapper.map(ar, ActivityRecordDto.class));
    }

    public boolean addActivityRecord(LocalDate date, Long duration, Long userActivityId) {
        if (activityRecords.exists(date, userActivityId)) {
            return false;
        }
        activityRecords.addActivityRecord(date, duration, userActivityId);
        return true;
    }
}
