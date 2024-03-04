package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.Role


interface RoleService {

    fun findById(id: Int): Role?
}