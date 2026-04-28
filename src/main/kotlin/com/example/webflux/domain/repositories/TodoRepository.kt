package com.example.webflux.domain.repositories

import com.example.webflux.domain.entities.Todo
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : CoroutineCrudRepository<Todo, Int> {
}