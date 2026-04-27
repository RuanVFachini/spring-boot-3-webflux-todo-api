package com.example.webflux.application.extensions

import com.example.webflux.application.responses.RegisterUserResponse
import org.springframework.security.core.userdetails.UserDetails

fun UserDetails.toResponse(): RegisterUserResponse = RegisterUserResponse(username = username)