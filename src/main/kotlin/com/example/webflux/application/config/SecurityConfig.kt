package com.example.webflux.application.config

import com.example.webflux.application.filters.JwtAuthFilter
import com.example.webflux.application.filters.JwtTokenService
import org.springframework.boot.web.servlet.DispatcherType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val jwtTokenService: JwtTokenService
) {

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        http
            .csrf { it.disable() }
//            .cors { corsConfigurer -> corsConfigurer.configure(http) }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .authorizeExchange {
//                it.dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()
                it.pathMatchers(HttpMethod.POST, "/api/auth/login").permitAll()
                it.pathMatchers(HttpMethod.POST, "/api/auth/register").permitAll()
                it.pathMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                it.pathMatchers(HttpMethod.GET, "/swagger-ui/*").permitAll()
                it.pathMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                it.anyExchange().authenticated()
            }

            .addFilterBefore(JwtAuthFilter(jwtTokenService), SecurityWebFiltersOrder.AUTHENTICATION)
//            .exceptionHandling {
//                it.authenticationEntryPoint { _, response, _ ->
//                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED)
//                }
//                it.accessDeniedHandler { _, response, _ ->
//                    response.sendError(HttpServletResponse.SC_FORBIDDEN)
//                }
//            }
        return http.build()
    }
}