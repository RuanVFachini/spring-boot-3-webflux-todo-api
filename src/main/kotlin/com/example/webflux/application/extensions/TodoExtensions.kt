package com.example.webflux.application.extensions

import com.example.webflux.application.requests.TodoRequest
import com.example.webflux.application.responses.TodoResponse
import com.example.webflux.domain.entities.Todo
import java.time.Instant

fun Todo.mapToResponse(): TodoResponse = TodoResponse(
    id = this.id,
    description = this.description,
    completed = this.completed,
    createdAt = this.createdAt,
    null
    )

fun TodoRequest.toEntity(): Todo = Todo(null, description, false, Instant.now())
