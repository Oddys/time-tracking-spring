package org.oddys.timetrackingspring.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {
    private Long userId;

    @NotNull
    private String login;

    @NotBlank
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotNull
    @Min(1)
    private Long roleId;

    private String roleName;
}
