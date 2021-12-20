package com.example.usermanagementservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class LoginDto {

    @NotNull
    private String userName;

    @NotNull
    private String password;

}
