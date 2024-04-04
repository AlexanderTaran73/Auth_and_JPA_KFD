package com.example.kotlinAuth.models

import com.example.kotlinAuth.services.RoleService
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.beans.factory.annotation.Autowired

// TODO add companion object
@Entity
class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var name = ""
}