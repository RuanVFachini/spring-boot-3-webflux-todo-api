package com.example.webflux.domain.services

import com.example.webflux.domain.entities.User
import org.springframework.security.core.userdetails.UserDetails

interface AuthService {

    suspend fun login(userName: String, password: String): String

    suspend fun register(userName: String, password: String): User

    suspend fun validateToken(token: String): UserDetails
}