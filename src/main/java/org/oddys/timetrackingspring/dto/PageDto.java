package org.oddys.timetrackingspring.dto;

import lombok.Data;

import java.util.List;

@Data
public class PageDto<E> {
    private final List<E> elements;
    private final int currentPage;
    private final int rowsPerPage;
    private final int numPages;
}
