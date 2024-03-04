package com.example.kotlinAuth.repositories

import com.example.kotlinAuth.models.FreeAnsSurvey
import com.example.kotlinAuth.models.MultyAnsSurvey
import com.example.kotlinAuth.models.SingleAnsSurvey
import com.example.kotlinAuth.models.Survey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SingleAnsSurveyRepository: JpaRepository<SingleAnsSurvey, Int> {
}

@Repository
interface MultyAnsSurveyRepository: JpaRepository<MultyAnsSurvey, Int> {
}

@Repository
interface FreeAnsSurveyRepository: JpaRepository<FreeAnsSurvey, Int>{
}