package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.ActivityDto;
import org.oddys.timetrackingspring.persist.ActivityAccess;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CommonService {
    private final ActivityAccess activityAccess;
    private final ModelMapper modelMapper;

    public boolean activityExists(String activityName) {
        Activity activity = activityAccess.findByName(activityName);
        return activity != null;
    }

    public Page<ActivityDto> findAll(int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityName"));
        Page<Activity> page = activityAccess.findAll(pageable);
        return page.map(a -> modelMapper.map(a, ActivityDto.class));
    }
}
