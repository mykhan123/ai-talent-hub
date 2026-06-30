package com.ai.talenthub.auth_service.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LogoutResponse {

    private String message;
}