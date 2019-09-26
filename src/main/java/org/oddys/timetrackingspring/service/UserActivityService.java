package org.oddys.timetrackingspring.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.dto.UserActivityDto;
import org.oddys.timetrackingspring.persist.UserActivityAccessor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserActivityService {
    private final UserActivityAccessor dao;
    private final ModelMapper modelMapper;

    public List<UserActivityDto> findUserActivityByUserId(Long userId) {
        return dao.findAllByUserId(userId).stream()
                .map(ua -> modelMapper.map(ua, UserActivityDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
