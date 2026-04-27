package com.example.webflux.domain.services

import com.example.webflux.domain.entities.User
import com.example.webflux.domain.repositories.UserRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class AuthServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val tokenService: TokenService,
    val authenticationManager: ReactiveAuthenticationManager
) : AuthService {
    override suspend fun login(
        userName: String,
        password: String
    ): String {
        val user = userRepository.findByUsername(userName)
            ?: throw UsernameNotFoundException(userName)

        val auth = UsernamePasswordAuthenticationToken(
            userName,
            password
        )

        authenticationManager.authenticate(auth)
        return tokenService.generateToken(user)
    }

    override suspend fun validateToken(token: String): UserDetails {
        val decoded = tokenService.validateToken(token.substringAfter("Bearer "))
        val userDetails = userRepository.findByUsername(decoded.subject)
            ?: throw UsernameNotFoundException(decoded.subject)
        return userDetails
    }

    override suspend fun register(userName: String, password: String): User {
        return userRepository.save(User(userName, passwordEncoder.encode(password)))
    }
}