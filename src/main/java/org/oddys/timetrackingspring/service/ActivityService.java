package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.persist.ActivityAccess;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class ActivityService {
    private final ActivityAccess activityAccess;
    private final ModelMapper modelMapper;

    public boolean check(String activityName) {
        Activity activity = activityAccess.findByName(activityName);
        return activity != null;
    }

    public Page<ActivityDto> findAll(Pageable pageable) {
        Page<Activity> page = activityAccess.findAll(pageable);
        return page.map(a -> modelMapper.map(a, ActivityDto.class));
    }
}
