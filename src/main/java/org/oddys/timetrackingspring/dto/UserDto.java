package org.oddys.timetrackingspring.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private Long roleId;
    private String roleName;
}
