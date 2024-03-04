package com.example.kotlinAuth.repositories

import com.example.kotlinAuth.models.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, Int> {
    fun findByEmail(email: String): User?


}