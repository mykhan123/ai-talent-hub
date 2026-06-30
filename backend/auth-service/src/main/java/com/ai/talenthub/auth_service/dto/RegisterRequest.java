package com.ai.talenthub.auth_service.dto;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "First Name is required")
    @Size(max = 50)
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Size(max = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone must be 10 digits")
    private String phone;

}