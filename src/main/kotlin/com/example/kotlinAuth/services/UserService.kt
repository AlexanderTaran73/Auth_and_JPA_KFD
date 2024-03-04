package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.User

interface UserService {

    fun save(user: User)

    fun deleteById(id: Int)

    fun findByEmail(email: String): User?

    fun findAll(): List<User>?
}