package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.*


interface SurveyService {
    fun findAll(): MutableList<Survey>

    fun publicFindAll(): MutableList<Survey>

    fun save(singleAnsSurvey: SingleAnsSurvey)

    fun save(multyAnsSurvey: MultyAnsSurvey)

    fun save(freeAnswerSurvey: FreeAnsSurvey)

    fun findByIdAndType(id: Int, surveyType: String): Survey?
    fun findByIdAndAnswerType(surveyId: Int, answerType: String): Survey?
}
