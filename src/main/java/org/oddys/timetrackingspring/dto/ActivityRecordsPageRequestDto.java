package org.oddys.timetrackingspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivityRecordsPageRequestDto {
    private Long userActivityId;
    private Boolean userActivityAssigned;
    private int currentPage;
    private int rowsPerPage;

    public ActivityRecordsPageRequestDto(ActivityRecordsPage dto) {
        userActivityId = dto.getUserActivityId();
        userActivityAssigned = dto.getAssigned();
        currentPage = dto.getCurrentPage();
        rowsPerPage = dto.getRowsPerPage();
    }
}
