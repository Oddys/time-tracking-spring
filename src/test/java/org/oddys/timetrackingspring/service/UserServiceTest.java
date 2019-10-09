package org.oddys.timetrackingspring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.UserActivities;
import org.oddys.timetrackingspring.persist.entity.UserActivity;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    private UserActivities userActivities;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserService service;

    @Test
    public void testRequestUserActivity_userActivityExists() {
        UserActivityDto dto = new UserActivityDto();
        dto.setUserId(1L);
        dto.setActivityId(1L);
        when(userActivities.exists(any(long.class), any(long.class))).thenReturn(true);
        assertFalse(service.requestUserActivity(dto));
    }

    @Test
    public void testRequestUserActivity_userActivityDoesNotExist() {
        UserActivityDto dto = new UserActivityDto();
        dto.setUserId(1L);
        dto.setActivityId(1L);
        when(userActivities.exists(any(long.class), any(long.class))).thenReturn(false);
        UserActivity userActivity = new UserActivity();
        userActivity.setUserActivityId(1L);
        when(modelMapper.map(dto, UserActivity.class)).thenReturn(userActivity);
        when(userActivities.addUserActivity(any(UserActivity.class))).thenReturn(userActivity);
        service.requestUserActivity(dto);
        verify(modelMapper).map(dto, UserActivity.class);
        verify(userActivities).addUserActivity(userActivity);
    }
}
