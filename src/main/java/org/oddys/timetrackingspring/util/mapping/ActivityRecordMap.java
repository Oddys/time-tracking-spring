package org.oddys.timetrackingspring.util.mapping;

import org.modelmapper.PropertyMap;
import org.oddys.timetrackingspring.dto.ActivityRecordDto;
import org.oddys.timetrackingspring.persist.entity.ActivityRecord;

public class ActivityRecordMap extends PropertyMap<ActivityRecord, ActivityRecordDto> {
    @Override
    protected void configure() {
        map().setUserId(source.getUserActivity().getUser().getUserId());
    }
}
