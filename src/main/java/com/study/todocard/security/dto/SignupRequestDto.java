package com.study.todocard.security.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    @NotBlank
    @Size(min=4, max=10)
    @Pattern(regexp = "^[a-z0-9]*")
    private String username;
    @NotBlank
    @Size(min=8, max=16)
    @Pattern(regexp = "^[a-zA-Z0-9]*")
    private String password;
    @Email
    @NotBlank
    private String email;
    private boolean admin = false;
    private String adminToken = "";
}