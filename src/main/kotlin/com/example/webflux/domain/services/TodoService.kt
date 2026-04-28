package com.example.webflux.domain.services

import com.example.webflux.domain.entities.Todo

interface TodoService {
    suspend fun all(): List<Todo>
    suspend fun save(entity: Todo): Todo
    suspend fun complete(id: Int): Todo
}