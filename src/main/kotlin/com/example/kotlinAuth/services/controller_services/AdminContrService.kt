package com.example.kotlinAuth.services.controller_services

import com.example.kotlinAuth.controllers.FreeAnsSurDTO
import com.example.kotlinAuth.controllers.MultyAnsSurDTO
import com.example.kotlinAuth.controllers.SingleAnsSurDTO
import com.example.kotlinAuth.models.FreeAnsSurvey
import com.example.kotlinAuth.models.MultyAnsSurvey
import com.example.kotlinAuth.models.PossibleAnswer
import com.example.kotlinAuth.models.SingleAnsSurvey
import com.example.kotlinAuth.services.SurveyService
import com.example.kotlinAuth.services.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class AdminContrService(
    private val userService: UserService,
    private val surveyService: SurveyService
) {

    fun getAllUsers(): ResponseEntity<Any> {
        return ResponseEntity(userService.findAll(), HttpStatus.OK)
    }

    fun createSingleAnsSurvey(singleAnsSurDTO: SingleAnsSurDTO): ResponseEntity<Any>{
        val singleAnsSurvey = SingleAnsSurvey().also {
            it.question = singleAnsSurDTO.question
        }

        singleAnsSurDTO.possibleAnswers.forEach(){
            val possibleAnswer = PossibleAnswer()
            possibleAnswer.answer = it.answer
            singleAnsSurvey.possibleAnswers.add(possibleAnswer)

        }

        surveyService.save(singleAnsSurvey)


        return ResponseEntity(singleAnsSurvey, HttpStatus.CREATED)
    }

    fun createMultyAnsSurvey(multyAnsSurDTO: MultyAnsSurDTO): ResponseEntity<Any>{
        val multyAnsSurvey = MultyAnsSurvey().also {
            it.question = multyAnsSurDTO.question
        }

        multyAnsSurDTO.possibleAnswers.forEach(){
            val possibleAnswer = PossibleAnswer()
            possibleAnswer.answer = it.answer
            multyAnsSurvey.possibleAnswers.add(possibleAnswer)
        }

        surveyService.save(multyAnsSurvey)

        return ResponseEntity(multyAnsSurvey, HttpStatus.CREATED)

    }

    fun createFreeAnsSurvey(freeAnsSurDTO: FreeAnsSurDTO): ResponseEntity<Any> {
        val freeAnsSurvey = FreeAnsSurvey().also {
            it.question = freeAnsSurDTO.question
        }

        surveyService.save(freeAnsSurvey)

        return ResponseEntity(freeAnsSurvey, HttpStatus.CREATED)
    }

    fun getAllSurvey(): ResponseEntity<Any> {
        return ResponseEntity(surveyService.findAll(), HttpStatus.OK)
    }

    fun getSurveyResult(id: Int, surveyType: String): ResponseEntity<Any> {
        val survey = surveyService.findByIdAndType(id, surveyType)
        if (survey == null){
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity(surveyService.findByIdAndType(id, surveyType) , HttpStatus.OK)

    }


}