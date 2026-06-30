package com.ai.talenthub.auth_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.talenthub.auth_service.dto.response.ApiResponse;
import com.ai.talenthub.auth_service.dto.response.UserResponse;
import com.ai.talenthub.auth_service.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
	private final AuthService authService;
//    @GetMapping("/me")
//    public String me() {
//
//        return "JWT Working Successfully";
//    }
    
    @GetMapping("/me")
    public ApiResponse<UserResponse> getCurrentUser() {

        return authService.getCurrentUser();
    }

}