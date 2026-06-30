package com.ai.talenthub.auth_service.service;

import com.ai.talenthub.auth_service.dto.RegisterRequest;
import com.ai.talenthub.auth_service.dto.request.LoginRequest;
import com.ai.talenthub.auth_service.dto.request.RefreshTokenRequest;
import com.ai.talenthub.auth_service.dto.response.ApiResponse;
import com.ai.talenthub.auth_service.dto.response.LoginResponse;
import com.ai.talenthub.auth_service.dto.response.LogoutResponse;
import com.ai.talenthub.auth_service.dto.response.RefreshTokenResponse;
import com.ai.talenthub.auth_service.dto.response.UserResponse;

public interface AuthService {

    ApiResponse<String> register(RegisterRequest request);
    
    ApiResponse<LoginResponse> login(LoginRequest request);
    
    ApiResponse<UserResponse> getCurrentUser();
    
    ApiResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request);
    
    ApiResponse<LogoutResponse> logout();

}