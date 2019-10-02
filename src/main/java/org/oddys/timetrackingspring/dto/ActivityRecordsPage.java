package org.oddys.timetrackingspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class ActivityRecordsPage extends PageDto<ActivityRecordDto> {
    private Long userActivityId;
    private Boolean assigned;

    public ActivityRecordsPage(List<ActivityRecordDto> elements, int currentPage,
            int rowsPerPage, int numPages, Long userActivityId, Boolean assigned) {
        super(elements, currentPage, rowsPerPage, numPages);
        this.userActivityId = userActivityId;
        this.assigned = assigned;
    }
}
