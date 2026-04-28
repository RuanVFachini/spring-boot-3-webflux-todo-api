package com.example.webflux.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant

@Table(value = "TODOS")
class Todo() {
    @Id
    var id: Long? = null
    lateinit var description: String
    var completed: Boolean = false
    lateinit var createdAt: Instant
    var completedAt: Instant? = null

    constructor(id: Long?, description: String, completed: Boolean, createdAt: Instant) : this() {
        this.id = id
        this.description = description
        this.completed = completed
        this.createdAt = createdAt
    }
}