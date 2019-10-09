package org.oddys.timetrackingspring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.persist.Activities;
import org.oddys.timetrackingspring.persist.UserActivities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
    @Mock
    private UserActivities userActivities;

    @Mock
    private Activities activities;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private AdminService service;

    @Test
    public void changeUserActivityStatus() {
        service.changeUserActivityStatus(1L, true);
        verify(userActivities).updateAssignedAndStatusChangeRequested(1L, false);
    }

    @Test
    public void testAddActivity_activityExists() {
        when(activities.exists(any(String.class))).thenReturn(true);
        assertFalse(service.addActivity("Doing stuff"));
    }

    @Test
    public void testAddActivity_activityDoesNotExist() {
        String name = "Doing stuff";
        when(activities.exists(any(String.class))).thenReturn(false);
        assertTrue(service.addActivity(name));
        verify(activities).add(name);
    }
}
