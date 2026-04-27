package com.example.webflux.application.filters

import org.springframework.context.annotation.Lazy
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class JwtAuthFilter(
    @param:Lazy private val jwtTokenService: JwtTokenService
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

class User : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        return listOf(SimpleGrantedAuthority("ROLE_USER"))
    }

    override fun getPassword(): String? {
        return "teste"
    }

    override fun getUsername(): String? {
        return "teste"
    }

}

@Service
class JwtTokenService() {

}
