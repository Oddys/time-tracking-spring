package org.oddys.timetrackingspring.dto;

import lombok.Data;

@Data
public class ActivityRecordsPageRequestDto {
    private Long userActivityId;
    private Boolean userActivityAssigned;
    private int currentPage;
    private int rowsPerPage;
}
