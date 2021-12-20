package com.example.usermanagementservice.dto;

import com.example.usermanagementservice.utils.ConstraintUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @NotNull
    @Length(min = 2, max = 20, message = ConstraintUtils.USERNAME_MESSAGE)
    private String userName;
    @NotNull
    @Length(min = 2, max = 20, message = ConstraintUtils.FIRSTNAME_MESSAGE)
    private String firstName;
    @NotNull
    @Length(min = 2, max = 20, message = ConstraintUtils.LASTNAME_MESSAGE)
    private String lastName;
    @Email
    private String email;
    @NotNull
    @Length(min = 2, max = 20, message = ConstraintUtils.PASSWORD_MESSAGE)
    private String password;

}
