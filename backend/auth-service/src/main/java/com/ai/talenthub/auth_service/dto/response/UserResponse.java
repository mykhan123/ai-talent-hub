package com.ai.talenthub.auth_service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private Boolean enabled;

    private Boolean accountNonLocked;

    private Boolean emailVerified;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}