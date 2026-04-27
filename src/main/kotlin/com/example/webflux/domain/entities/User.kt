package com.example.webflux.domain.entities

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Sequence
import org.springframework.data.relational.core.mapping.Table
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Table("USERS")
class User() : UserDetails {

    @Id
    var id: Long? = null
    @Column()
    private lateinit var username: String
    @Column()
    private lateinit var hash: String

    constructor(username: String, hash: String) : this() {
        this.username = username
        this.hash = hash
    }

    override fun getAuthorities(): Collection<GrantedAuthority?>? = listOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_ADMIN"))

    override fun getPassword(): String? = hash

    override fun getUsername(): String? = username

}