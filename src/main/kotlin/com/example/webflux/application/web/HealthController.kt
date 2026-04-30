package com.example.webflux.application.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {

    @RequestMapping(value = ["/health"], method = [(RequestMethod.GET)])
    suspend fun health(): ResponseEntity<String> {
        return ResponseEntity.ok("Healthy")
    }
}