package org.oddys.timetrackingspring.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserRequestDto {
    @NotBlank
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Min(1)
    private Long roleId;

    @NotBlank
    private String roleName;
}
