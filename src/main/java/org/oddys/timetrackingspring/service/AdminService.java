package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.Activities;
import org.oddys.timetrackingspring.persist.UserActivities;
import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class AdminService {
    private final UserActivities userActivities;
    private final Activities activities;
    private final ModelMapper modelMapper;

    @Transactional(readOnly = true)
    public Page<UserActivityDto> showRequestsForStatusChange(int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage, Sort.by("user.userId"));
        Page<UserActivity> page = userActivities.findRequestsForStatusChange(pageable);
        return page.map(ua -> modelMapper.map(ua, UserActivityDto.class));
    }

    public void changeUserActivityStatus(Long userActivityId, Boolean currentStatus) {
        Boolean newStatus = !currentStatus;
        userActivities.updateAssignedAndStatusChangeRequested(userActivityId, newStatus);
    }

    public boolean addActivity(String name) {
        if (activities.exists(name)) {
            return false;
        }
        activities.add(name);
        return true;
    }
}
