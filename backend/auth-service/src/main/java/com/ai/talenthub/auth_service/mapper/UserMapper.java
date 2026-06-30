package com.ai.talenthub.auth_service.mapper;

import com.ai.talenthub.auth_service.dto.response.UserResponse;
import com.ai.talenthub.auth_service.entity.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .enabled(user.getEnabled())
                .accountNonLocked(user.getAccountNonLocked())
                .emailVerified(user.getEmailVerified())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}