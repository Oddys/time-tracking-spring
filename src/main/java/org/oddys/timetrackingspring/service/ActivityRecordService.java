package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.ActivityRecordDto;
import org.oddys.timetrackingspring.persist.ActivityRecordAccess;
import org.oddys.timetrackingspring.persist.entity.ActivityRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ActivityRecordService {
    private final ModelMapper modelMapper;
    private final ActivityRecordAccess activityRecordAccess;

    public Page<ActivityRecordDto> findAll(int currentPage, int rowsPerPage) {
        Pageable pageable = PageRequest.of(currentPage, rowsPerPage,
                Sort.by("activityDate").descending());
        Page<ActivityRecord> page = activityRecordAccess.findAll(pageable);
        return page.map(ar -> modelMapper.map(ar, ActivityRecordDto.class));
    }
}
