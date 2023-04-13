package com.storage.dto.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserRegisterDto {

    @NotNull
    @Size(min = 4, max = 12, message = "Username must be between 4 and 12 characters")
    private String username;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    @Size(min = 4, max = 12, message = "Password must be between 4 and 12 characters")
    private String password;
}
