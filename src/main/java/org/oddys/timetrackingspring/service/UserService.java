package org.oddys.timetrackingspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.UserActivities;
import org.oddys.timetrackingspring.persist.entity.UserActivity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * A class that provides services associated with an ordinary user of the application.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService {
    private final UserActivities userActivities;
    private final ModelMapper modelMapper;

    /**
     * Retrieves a list of user activities associated with a given user.
     *
     * @param userId an id of the user whose activities should be found
     * @return a list of UserActivityDtos
     */
    @Transactional(readOnly = true)
    public List<UserActivityDto> findUserActivityByUserId(Long userId) {
        return userActivities.findAllByUserId(userId).stream()
                .map(ua -> modelMapper.map(ua, UserActivityDto.class))
                .collect(Collectors.toList());
    }

    /**
     * Requests for assigning a given activity to a given user
     * @param dto a Data Transfer Object containing information about the activity and the user
     * @return true if a request for assigning was created, false otherwise
     */
    public boolean requestUserActivity(UserActivityDto dto) {
        if (userActivities.exists(dto.getUserId(), dto.getActivityId())) {
            return false;
        }
        UserActivity userActivity = modelMapper.map(dto, UserActivity.class);
        return userActivities.addUserActivity(userActivity).getUserActivityId() != null;
    }

    /**
     * Requests for reverting the status of a given user activity.
     *
     * @param userActivityId the id of a user activity whose status should be changed
     * @return true if a request for status changing was created, false otherwise
     */
    public boolean requestUserActivityStatusChange(Long userActivityId) {
        return userActivities.requestStatusChange(userActivityId);
    }
}
