package com.storage.dto.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserPublic {

    private Long id;
    private String username;
    private String planName;
    private String email;
}
