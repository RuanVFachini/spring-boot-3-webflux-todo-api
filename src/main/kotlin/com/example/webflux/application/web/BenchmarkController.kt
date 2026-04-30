package com.example.webflux.application.web

import com.example.webflux.application.extensions.mapToResponse
import com.example.webflux.application.extensions.toEntity
import com.example.webflux.application.extensions.toResponse
import com.example.webflux.application.requests.LoginRequest
import com.example.webflux.application.requests.RegisterUserRequest
import com.example.webflux.application.requests.TodoRequest
import com.example.webflux.application.responses.LoginResponse
import com.example.webflux.application.responses.RegisterUserResponse
import com.example.webflux.application.responses.TodoResponse
import com.example.webflux.domain.entities.Todo
import com.example.webflux.domain.services.AuthService
import com.example.webflux.domain.services.TodoService
import kotlinx.coroutines.delay
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.time.Duration.Companion.milliseconds

@RestController
@RequestMapping("/api/benchmark")
class BenchmarkController(
    private val todoService: TodoService
) {

    @PostMapping
    suspend fun login(@Validated @RequestBody request: TodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity.status(HttpStatus.OK).body(
            todoService.save(request.toEntity()).mapToResponse()
        )
    }
}