package com.example.kotlinAuth.repositories

import com.example.kotlinAuth.models.PossibleAnswer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface PossibleAnswerRepository: JpaRepository<PossibleAnswer, Int> {
}