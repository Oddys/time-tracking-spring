package org.oddys.timetrackingspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ActivitiesPageDto {
    private List<ActivityDto> activities;
    private int currentPage;
    private int rowsPerPage;
    private int numPages;
}
