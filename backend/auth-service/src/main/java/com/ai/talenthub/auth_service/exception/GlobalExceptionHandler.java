package com.ai.talenthub.auth_service.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ai.talenthub.auth_service.dto.response.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
		@ExceptionHandler(MethodArgumentNotValidException.class)
	   public ResponseEntity<ApiResponse<Map<String, String>>>  handleValidationException(MethodArgumentNotValidException ex){
			
			 Map<String, String> errors = new HashMap<>();
			 ex.getBindingResult().getFieldErrors().forEach(error ->
             errors.put(error.getField(), error.getDefaultMessage()));
			 
			 ApiResponse<Map<String, String>> response =
		                new ApiResponse<>(false, "Validation Failed", errors);
			 return ResponseEntity.badRequest().body(response);
		
	}
}
