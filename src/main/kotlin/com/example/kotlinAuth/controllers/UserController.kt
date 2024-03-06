package com.example.kotlinAuth.controllers

import com.example.kotlinAuth.models.Answer
import com.example.kotlinAuth.models.PossibleAnswer
import com.example.kotlinAuth.services.controller_services.UserContrService
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.annotation.JsonTypeName
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

    @PostMapping("/surveys/answerSurvey")
    fun answerSurvey(@RequestBody answerDTO: AnswerDTO, request: HttpServletRequest): ResponseEntity<Any>{
        return userContrService.answerSurvey(answerDTO, request)
    }
}
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "answerType", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SingleAnsDTO::class, name = "SingleAns"),
    JsonSubTypes.Type(value = MultyAnsDTO::class, name = "MultyAns"),
    JsonSubTypes.Type(value = FreeAnsDTO::class, name = "FreeAns")
)
open class AnswerDTO(
    open val surveyId: Int,
    open val answerType: String
)

class SingleAnsDTO(
    override val surveyId: Int,
    override val answerType: String,
    val possibleAnswer: PossibleAnswer
) : AnswerDTO(surveyId, answerType)

class MultyAnsDTO(
    override val surveyId: Int,
    override val answerType: String,
    val possibleAnswers: MutableList<PossibleAnswer>
) : AnswerDTO(surveyId, answerType)

class FreeAnsDTO(
    override val surveyId: Int,
    override val answerType: String,
    val answer: String
) : AnswerDTO(surveyId, answerType)
