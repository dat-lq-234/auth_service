package com.datlq.auth_service.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;

public record LoginRequest(
        @NotBlank(message = "Email can not blank")
        @Email(message = "Invalid email")
        String email,

        @Size(min = 5, max = 20, message = "Password length must be in 5 - 20 character")
        String password
) {
}
