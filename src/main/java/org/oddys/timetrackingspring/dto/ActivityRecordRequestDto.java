package org.oddys.timetrackingspring.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ActivityRecordRequestDto {
    Long userActivityId;
    LocalDate date;
    Long duration;
}
