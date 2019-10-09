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

/**
 * A class that provides services common to all types of users.
 */
@Service
@AllArgsConstructor
@Transactional
public class CommonService {
    private final Activities activities;
    private final ActivityRecords activityRecords;
    private final ModelMapper modelMapper;

    /**
     * Retrieves a Page of ActivityDtos.
     *
     * @param currentPage a current page number
     * @param rowsPerPage a number rows per page
     * @return a Page object containing a specified portion ofActivityDtos from the database
     */
    @Transactional(readOnly = true)
    public Page<ActivityDto> findAll(int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityName"));
        Page<Activity> page = activities.findAll(pageable);
        return page.map(a -> modelMapper.map(a, ActivityDto.class));
    }

    /**
     * Retrievs a Page of ActivityRecordDtos.
     *
     * @param userActivityId an id of UserActivity for which activity records should be retrieved
     * @param currentPage a current page number
     * @param rowsPerPage a number rows per page
     * @return a Page object containing a specified portion ofActivityRecordDtos from the database
     */
    @Transactional(readOnly = true)
    public Page<ActivityRecordDto> findAll(Long userActivityId, int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityDate").descending());
        Page<ActivityRecord> page = activityRecords.findAll(userActivityId, pageable);
        return page.map(ar -> modelMapper.map(ar, ActivityRecordDto.class));
    }

    /**
     *
     * @param date a date of ActivityRecord that should be created
     * @param duration a duration of ActivityRecord that should be created
     * @param userActivityId an id of UserActivity for which a new ActivityRecord should be created
     * @return true if new ActivityRecord was created, false otherwise
     */
    public boolean addActivityRecord(LocalDate date, Long duration, Long userActivityId) {
        if (activityRecords.exists(date, userActivityId)) {
            return false;
        }
        activityRecords.addActivityRecord(date, duration, userActivityId);
        return true;
    }
}
