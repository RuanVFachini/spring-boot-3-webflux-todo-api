package com.example.webflux.domain.services

import com.example.webflux.domain.repositories.UserRepository
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import kotlin.jvm.optionals.getOrElse

@Service
class UserDetailsServiceImpl(
    private val userRepository: UserRepository
) : ReactiveUserDetailsService {

    override fun findByUsername(username: String?): Mono<UserDetails?>? =  mono {
        userRepository.findByUsername(username)
    }
}