package com.example.kotlinAuth.models

import jakarta.persistence.*


@Entity
@Table(name="users")
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(unique = true)
    var email = ""

    @Column
    var password = ""

//    TODO add ManyToMany for roles and users
    @ManyToOne(optional = false)
    @JoinColumn
    var roleid: Role? = null
}