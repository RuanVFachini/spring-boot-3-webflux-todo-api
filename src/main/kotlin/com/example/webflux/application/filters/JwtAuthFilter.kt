package com.example.webflux.application.filters

import com.example.webflux.domain.services.TokenService
import com.example.webflux.domain.entities.User
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

class JwtAuthFilter(
    private val jwtTokenService: TokenService
) : WebFilter {
    override fun filter(
        exchange: ServerWebExchange,
        chain: WebFilterChain
    ): Mono<Void?> {
         val authentication = UsernamePasswordAuthenticationToken(
             User(),
            null,
            User().authorities
        )

        return chain.filter(exchange)
            .contextWrite(
                ReactiveSecurityContextHolder.withAuthentication(authentication)
            )
    }
}
