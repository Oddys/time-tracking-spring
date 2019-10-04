package org.oddys.timetrackingspring.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserRequestDto {
    private Long userId;

    @NotBlank(message = "{field.notblank}")
    private String login;

    @NotBlank(message = "{field.notblank}")
    private String password;

    @NotBlank(message = "{field.notblank}")
    private String firstName;

    @NotBlank(message = "{field.notblank}")
    private String lastName;

    @NotNull(message = "{field.notblank}")
    @Min(value = 1, message = "{field.positive}")
    private Long roleId;

    private String roleName;
}
