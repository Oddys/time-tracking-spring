package org.oddys.timetrackingspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oddys.timetrackingspring.persist.ActivityAccess;
import org.oddys.timetrackingspring.persist.entity.Activity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AddActivityService {
    private final ActivityAccess activityAccess;

    public boolean check(String activityName) {
        Activity activity = activityAccess.findByName(activityName);
        return activity != null;
    }
}
