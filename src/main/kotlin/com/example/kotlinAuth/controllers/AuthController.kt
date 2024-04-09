package com.example.kotlinAuth.controllers

import com.example.kotlinAuth.services.controller_services.AuthContrService
import io.micrometer.observation.annotation.Observed
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@Observed(name = "AuthController")
@RequestMapping("/auth")
class AuthController(
    private val authContrService: AuthContrService
) {

    @PostMapping
    fun Authorization(@RequestBody authDTO: AuthDTO): ResponseEntity<Any>{
        return authContrService.Authorization(authDTO)
    }

    @PostMapping("/reg_user")
    fun Registration(@RequestBody authDTO: AuthDTO): ResponseEntity<Any>{
        return authContrService.Registration(authDTO)
    }


}

class AuthDTO(
    val email: String,
    val password: String
    )

class TokenDTO(
    val token: String
    )

class Message(
    val message: String
)