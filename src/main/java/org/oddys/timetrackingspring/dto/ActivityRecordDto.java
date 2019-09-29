package org.oddys.timetrackingspring.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityRecordDto {
    private Long id;
    private String activityName;
    private Boolean userActivityStatusChangeRequested;
    private LocalDate activityDate;
    private Long duration;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long activityId;
}
