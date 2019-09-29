package org.oddys.timetrackingspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActivityRecordsPageDto {
    private List<ActivityRecordDto> activityRecords;
    private int numPages;
    private Long userActivityId;
    private Boolean userActivityAssigned;
    private int currentPage;
    private int rowsPerPage;

    public ActivityRecordsPageDto(List<ActivityRecordDto> activityRecords, int numPages,
            ActivityRecordsPageRequestDto requestDto) {
        this.activityRecords = activityRecords;
        this.numPages = numPages;
        userActivityId = requestDto.getUserActivityId();
        userActivityAssigned = requestDto.getUserActivityAssigned();
        currentPage = requestDto.getCurrentPage();
        rowsPerPage = requestDto.getRowsPerPage();
    }
}
