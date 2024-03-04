package com.example.kotlinAuth.repositories

import com.example.kotlinAuth.models.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository: JpaRepository<Role, Int> {
}