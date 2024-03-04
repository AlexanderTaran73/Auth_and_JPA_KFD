package com.example.kotlinAuth.repositories

import com.example.kotlinAuth.models.Answer
import com.example.kotlinAuth.models.FreeAnswer
import com.example.kotlinAuth.models.MultyAnswer
import com.example.kotlinAuth.models.SingleAnswer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface SingleAnswerRepository: JpaRepository<SingleAnswer, Int> {
}

@Repository
interface MultyAnswerRepository: JpaRepository<MultyAnswer, Int> {
}

@Repository
interface FreeAnswerRepository: JpaRepository<FreeAnswer, Int>{
}