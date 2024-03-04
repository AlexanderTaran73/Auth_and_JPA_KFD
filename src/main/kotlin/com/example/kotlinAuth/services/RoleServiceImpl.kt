package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.Role
import com.example.kotlinAuth.repositories.RoleRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class RoleServiceImpl: RoleService {

    @Autowired
    lateinit var roleRepository: RoleRepository

    override fun findById(id: Int): Role? {
        return roleRepository.findById(id).get()
    }
}