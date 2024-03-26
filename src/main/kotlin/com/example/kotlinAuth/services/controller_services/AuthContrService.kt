package com.example.kotlinAuth.services.controller_services

import com.example.kotlinAuth.config.jwt.JwtProvider
import com.example.kotlinAuth.controllers.AuthDTO
import com.example.kotlinAuth.controllers.Message
import com.example.kotlinAuth.controllers.TokenDTO
import com.example.kotlinAuth.models.User
import com.example.kotlinAuth.services.RoleService
import com.example.kotlinAuth.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AuthContrService (
    private val userService: UserService,
    private val roleService: RoleService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider
) {

    fun Authorization(authDTO: AuthDTO): ResponseEntity<Any> {
        val user = userService.findByEmail(authDTO.email)
        if (user == null) return ResponseEntity(Message("User with this email was not found"), HttpStatus.BAD_REQUEST)
        if (!passwordEncoder.matches(authDTO.password, user.password)) return ResponseEntity(Message("Password is not correct"), HttpStatus.BAD_REQUEST)
        //        TODO change getting roles
        return ResponseEntity(TokenDTO(jwtProvider.generateToken(user.email, List(1){user.roleid!!.name})), HttpStatus.OK)
    }

    fun Registration(authDTO: AuthDTO): ResponseEntity<Any> {
        val user = User()

        if (userService.findByEmail(authDTO.email)!=null) return ResponseEntity(Message("User with this email has already been created"), HttpStatus.BAD_REQUEST)
        user.email = authDTO.email
        user.password = passwordEncoder.encode(authDTO.password)
        user.roleid = roleService.findById(0)
        userService.save(user)
        //        TODO change getting roles
        return ResponseEntity(TokenDTO(jwtProvider.generateToken(user.email, List(1){user.roleid!!.name})), HttpStatus.CREATED)
    }

}