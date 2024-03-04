package com.example.kotlinAuth.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import jakarta.persistence.MappedSuperclass
import jakarta.persistence.OneToOne


@MappedSuperclass
abstract class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @OneToOne
    @JoinColumn
    var user: User? = null
}

@Entity
class SingleAnswer: Answer(){

    @ManyToOne(optional = false)
    @JoinColumn
    var possibleAnswer: PossibleAnswer? = null
}

@Entity
class MultyAnswer: Answer(){
    @ManyToMany
    @JoinTable
    var possibleAnswers: MutableList<PossibleAnswer> = mutableListOf()
}

@Entity
class FreeAnswer: Answer(){
    @Column
    var answer: String? = null
}