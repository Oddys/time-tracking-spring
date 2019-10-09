package org.oddys.timetrackingspring.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.persist.Activities;
import org.oddys.timetrackingspring.persist.ActivityRecords;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommonServiceTest {
    @Mock
    private Activities activities;

    @Mock
    private ActivityRecords activityRecords;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CommonService service;

    @Test
    public void testAddActivity_ActivityExists() {
        when(activityRecords.exists(any(LocalDate.class), any(long.class))).thenReturn(true);
        assertFalse(service.addActivityRecord(LocalDate.now(), 120L, 1L));
    }

    @Test
    public void testAddActivity_ActivityDoesNotExist() {
        when(activityRecords.exists(any(LocalDate.class), any(long.class))).thenReturn(false);
        assertTrue(service.addActivityRecord(LocalDate.now(), 120L, 1L));
        verify(activityRecords).addActivityRecord(LocalDate.now(), 120L, 1L);
    }
}
