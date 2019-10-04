package org.oddys.timetrackingspring.util.mapping;

import org.modelmapper.PropertyMap;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.entity.UserActivity;

public class UserActivityMap extends PropertyMap<UserActivity, UserActivityDto> {
    @Override
    protected void configure() {
        map().setActivityId(source.getActivity().getActivityId());
        map().setUserId(source.getUser().getUserId());
    }
}
