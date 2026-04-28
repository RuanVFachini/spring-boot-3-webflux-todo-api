package com.example.webflux.domain.services

import com.example.webflux.domain.entities.Todo
import com.example.webflux.domain.repositories.TodoRepository
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TodoServiceImpl(
    private val repository: TodoRepository
) : TodoService {
    override suspend fun all(): List<Todo> {
        return repository.findAll().toList()
    }


    override suspend fun save(entity: Todo): Todo {
        return repository.save(entity)
    }

    override suspend fun complete(id: Int): Todo {
        val entity = repository.findById(id) ?: throw Exception("Entity not found with id: $id")

        entity.completed = true
        entity.completedAt = Instant.now()

        return repository.save(entity)
    }
}