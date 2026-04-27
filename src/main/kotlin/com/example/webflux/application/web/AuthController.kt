package com.example.webflux.application.web

import com.example.webflux.application.extensions.toResponse
import com.example.webflux.application.requests.LoginRequest
import com.example.webflux.application.requests.RegisterUserRequest
import com.example.webflux.application.responses.LoginResponse
import com.example.webflux.application.responses.RegisterUserResponse
import com.example.webflux.domain.services.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val service: AuthService
) {

    @PostMapping()
    @RequestMapping("/register")
    suspend fun register(@Validated @RequestBody request: RegisterUserRequest): ResponseEntity<RegisterUserResponse> {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            service.register(request.email, request.password).toResponse()
        )
    }

    @PostMapping("/login")
    suspend fun login(@Validated @RequestBody request: LoginRequest): ResponseEntity<LoginResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(
            LoginResponse(
                service.login(request.email, request.password)
            )
        )
    }
}