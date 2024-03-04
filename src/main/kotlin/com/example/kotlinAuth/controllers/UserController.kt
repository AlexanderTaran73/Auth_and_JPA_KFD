package com.example.kotlinAuth.controllers

import com.example.kotlinAuth.models.Answer
import com.example.kotlinAuth.models.PossibleAnswer
import com.example.kotlinAuth.services.controller_services.UserContrService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/user")
class UserController(
    private val userContrService: UserContrService
) {

    @GetMapping("/surveys/getAllSurvey")
    fun getAllSurvey(): ResponseEntity<Any>{
        return userContrService.getAllSurvey()
    }
//    TODO: fix answer
    @PostMapping("/surveys/answerSurvey")
    fun answerSurvey(@RequestBody answerDTO: AnswerDTO, request: HttpServletRequest): ResponseEntity<Any>{
        answerDTO as SingleAnsDTO
        println(answerDTO.possibleAnswer.answer)
        return userContrService.answerSurvey(answerDTO, request)
    }
}


open class AnswerDTO (
    open val surveyId: Int,
    open val surveyType: String
)

class SingleAnsDTO(
    override val surveyId: Int,
    override val surveyType: String,
    val possibleAnswer: PossibleAnswer
): AnswerDTO(surveyId, surveyType)

class MultyAnsDTO(
    override val surveyId: Int,
    override val surveyType: String,
    val possibleAnswers: MutableList<PossibleAnswer>
): AnswerDTO(surveyId, surveyType)

class FreeAnsDTO(
    override val surveyId: Int,
    override val surveyType: String,
    val answer: String
): AnswerDTO(surveyId, surveyType)

