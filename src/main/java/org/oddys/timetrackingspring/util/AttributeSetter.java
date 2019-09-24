package org.oddys.timetrackingspring.util;

import lombok.NoArgsConstructor;
import org.oddys.timetrackingspring.dto.ActivityDto;

import javax.servlet.http.HttpSession;
import java.util.List;

@NoArgsConstructor
public class AttributeSetter {
    public void setShowActivities(HttpSession session, List<ActivityDto> activities,
            int currentPage, int rowsPerPage, int numPages) {
        session.setAttribute("activities", activities);
        session.setAttribute("currentPage", currentPage);
        session.setAttribute("rowsPerPage", rowsPerPage);
        session.setAttribute("numPages", numPages);
    }
}
