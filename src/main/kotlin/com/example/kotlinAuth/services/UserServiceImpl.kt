package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.User
import com.example.kotlinAuth.repositories.UserRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


@Service
@Transactional
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {



    override fun save(user: User) {
        userRepository.save(user)
    }

    override fun deleteById(id: Int) {
        userRepository.deleteById(id)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun findAll(): List<User>? {
        return userRepository.findAll()
    }
}