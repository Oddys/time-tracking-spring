package org.oddys.timetrackingspring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.UserActivities;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserActivityService {
    private final UserActivities dao;
    private final ModelMapper modelMapper;

    public List<UserActivityDto> findUserActivityByUserId(Long userId) {
        return dao.findAllByUserId(userId).stream()
                .map(ua -> modelMapper.map(ua, UserActivityDto.class))
                .collect(Collectors.toList());
    }
}
