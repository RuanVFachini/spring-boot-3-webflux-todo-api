package com.example.webflux.application.requests

import jakarta.validation.constraints.NotEmpty

data class RegisterUserRequest(
    @param:NotEmpty val email: String,
    @param:NotEmpty val password: String
)
