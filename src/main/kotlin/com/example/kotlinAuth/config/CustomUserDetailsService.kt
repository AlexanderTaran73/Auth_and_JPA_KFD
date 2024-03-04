package com.example.kotlinAuth.config

import com.example.kotlinAuth.services.UserService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class CustomUserDetailsService(private val userService: UserService) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val userEntity = userService.findByEmail(email)
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userEntity!!)
    }
}
