package org.oddys.timetrackingspring.dto;

import lombok.Data;

@Data
public class UserActivityDto {
    private Long userActivityId;
    private Boolean assigned;
    private Boolean statusChangeRequested;
    private Long userId;
    private String userFirstName;
    private String userLastName;
    private Long activityId;
    private String activityName;
}
