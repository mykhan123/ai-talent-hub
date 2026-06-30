package com.ai.talenthub.auth_service.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ai.talenthub.auth_service.dto.RegisterRequest;
import com.ai.talenthub.auth_service.dto.request.LoginRequest;
import com.ai.talenthub.auth_service.dto.request.RefreshTokenRequest;
import com.ai.talenthub.auth_service.dto.response.ApiResponse;
import com.ai.talenthub.auth_service.dto.response.LoginResponse;
import com.ai.talenthub.auth_service.dto.response.LogoutResponse;
import com.ai.talenthub.auth_service.dto.response.RefreshTokenResponse;
import com.ai.talenthub.auth_service.dto.response.UserResponse;
import com.ai.talenthub.auth_service.entity.User;
import com.ai.talenthub.auth_service.mapper.UserMapper;
import com.ai.talenthub.auth_service.repository.UserRepository;
import com.ai.talenthub.auth_service.security.jwt.JwtService;
import com.ai.talenthub.auth_service.service.AuthService;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ai.talenthub.auth_service.security.CustomUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public ApiResponse<String> register(RegisterRequest request) {

       // return new ApiResponse<>(true,"Register API Working",null);

    
    if (userRepository.existsByEmail(request.getEmail())) {
        return new ApiResponse(false, "Email already exists", null);
    }

    User user = new User();

    user.setFirstName(request.getFirstName());
    user.setLastName(request.getLastName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());

    user.setPassword(passwordEncoder.encode(request.getPassword()));

    User savedUser= userRepository.save(user);
    return new ApiResponse(true, "User Registered Successfully", UserMapper.toResponse(savedUser));
    }
    
    
    @Override
    public ApiResponse<LoginResponse> login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String accessToken = jwtService.generateAccessToken(user.getEmail());

        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        LoginResponse response = LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .tokenType("Bearer")
                .expiresIn(3600L)
                .build();

        return new ApiResponse<>(
                true,
                "Login Successful",
                response
        );
    }


    @Override
    public ApiResponse<UserResponse> getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails currentUser =
                (CustomUserDetails) authentication.getPrincipal();

        User user = userRepository.findById(currentUser.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ApiResponse<>(
                true,
                "Current User Details",
                UserMapper.toResponse(user)
        );
    }
    
    
    @Override
    public ApiResponse<RefreshTokenResponse> refreshToken(RefreshTokenRequest request) {

        // Refresh Token
        String refreshToken = request.getRefreshToken();

        // Email nikalo
        String email = jwtService.extractUsername(refreshToken);

        // User nikalo
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Token verify
        if (!jwtService.isTokenValid(refreshToken, new CustomUserDetails(user))) {
            throw new RuntimeException("Invalid Refresh Token");
        }

        // Naya Access Token banao
        String accessToken = jwtService.generateAccessToken(email);

        // Response
        RefreshTokenResponse response = RefreshTokenResponse.builder()
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(3600L)
                .build();

        return new ApiResponse<>( true,"Access Token Generated Successfully",response);
    }
    
    @Override
    public ApiResponse<LogoutResponse> logout() {

        LogoutResponse response = LogoutResponse.builder()
                .message("Logout Successful")
                .build();

        return new ApiResponse<>(true,"Logout Successful",response);
    }
	
}