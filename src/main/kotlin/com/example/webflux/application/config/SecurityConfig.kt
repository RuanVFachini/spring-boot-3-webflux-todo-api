package com.example.webflux.application.config

import com.example.webflux.application.filters.JwtAuthFilter
import com.example.webflux.domain.services.TokenService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository


@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val jwtTokenService: TokenService
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf { it.disable() }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange {
                it.pathMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                it.pathMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                it.pathMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                it.pathMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                it.pathMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                it.anyExchange().authenticated()
            }

            .addFilterBefore(JwtAuthFilter(jwtTokenService), SecurityWebFiltersOrder.AUTHENTICATION)
        return http.build()
    }

    @Bean
    fun authenticationManager(
        userDetailsService: ReactiveUserDetailsService,
        passwordEncoder: PasswordEncoder
        ): ReactiveAuthenticationManager {
        val manager =
            UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService)

        manager.setPasswordEncoder(passwordEncoder)
        return manager
    }
}