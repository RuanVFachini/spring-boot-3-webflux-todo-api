package com.example.webflux.application.web

import com.example.webflux.application.extensions.mapToResponse
import com.example.webflux.application.extensions.toEntity
import com.example.webflux.application.requests.TodoRequest
import com.example.webflux.application.responses.TodoResponse
import com.example.webflux.domain.entities.Todo
import com.example.webflux.domain.services.TodoService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("api/todos")
class TodoController(
    private val service: TodoService
) {


    @GetMapping
    suspend fun all() = service.all().map { it.mapToResponse() }

    @PostMapping
    suspend fun create(@RequestBody request: TodoRequest): TodoResponse {
        val entity = request.toEntity()
        return service.save(entity).mapToResponse()
    }

    @PostMapping("/{id}/complete")
    suspend fun complete(@PathVariable id: Int) = service.complete(id).mapToResponse()
}