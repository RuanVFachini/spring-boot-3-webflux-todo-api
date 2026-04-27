package com.example.webflux.domain.repositories

import com.example.webflux.domain.entities.User
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UserRepository : CoroutineCrudRepository<User, Int> {
    suspend fun findByUsername(subject: String?): UserDetails?
}