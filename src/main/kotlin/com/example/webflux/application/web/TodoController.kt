package com.example.webflux.application.web

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/todos")
class TodoController {

    @GetMapping
    suspend fun all(): String {
        delay(10000L)
        return "teste"
    }
}