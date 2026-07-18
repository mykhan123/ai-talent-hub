package com.ai.talenthub.auth_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ai.talenthub.auth_service.dto.RegisterRequest;
import com.ai.talenthub.auth_service.dto.request.LoginRequest;
import com.ai.talenthub.auth_service.dto.request.RefreshTokenRequest;
import com.ai.talenthub.auth_service.dto.response.ApiResponse;
import com.ai.talenthub.auth_service.dto.response.LoginResponse;
import com.ai.talenthub.auth_service.dto.response.LogoutResponse;
import com.ai.talenthub.auth_service.dto.response.RefreshTokenResponse;
import com.ai.talenthub.auth_service.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<String>>  register(@Valid @RequestBody RegisterRequest request){
		System.out.println("******** REGISTER API HIT ********");
		return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(
	        @Valid @RequestBody LoginRequest request){
		System.out.println("********Login API is calling");
	    return ResponseEntity.ok(authService.login(request));
	}
	
	@PostMapping("/refresh")
	public ResponseEntity<ApiResponse<RefreshTokenResponse>> refreshToken(
	        @RequestBody RefreshTokenRequest request){

	    return ResponseEntity.ok(authService.refreshToken(request));
	}
	
	@PostMapping("/logout")
	public ResponseEntity<ApiResponse<LogoutResponse>> logout(){

	    return ResponseEntity.ok(authService.logout());

	}
}
