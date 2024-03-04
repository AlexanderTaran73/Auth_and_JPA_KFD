package com.example.kotlinAuth.services.controller_services

import com.example.kotlinAuth.config.jwt.JwtFilter
import com.example.kotlinAuth.config.jwt.JwtProvider
import com.example.kotlinAuth.controllers.AnswerDTO
import com.example.kotlinAuth.controllers.FreeAnsDTO
import com.example.kotlinAuth.controllers.MultyAnsDTO
import com.example.kotlinAuth.controllers.SingleAnsDTO
import com.example.kotlinAuth.models.*
import com.example.kotlinAuth.services.SurveyService
import com.example.kotlinAuth.services.UserService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils


@Service
class UserContrService(
    private val surveyService: SurveyService,
    private val jwtProvider: JwtProvider,
    private val userService: UserService
) {

    fun getAllSurvey(): ResponseEntity<Any> {
        return ResponseEntity(surveyService.publicFindAll(), HttpStatus.OK)
    }

    fun answerSurvey(answerDTO: AnswerDTO, request: HttpServletRequest): ResponseEntity<Any> {
        val bearer = request.getHeader(JwtFilter.AUTHORIZATION)
        val token = if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) { bearer.substring(7) } else null
        val user = userService.findByEmail(jwtProvider.getEmailFromToken(token))

        val survey = surveyService.findByIdAndType(answerDTO.surveyId, answerDTO.surveyType)
        if (survey == null || user == null) return ResponseEntity(HttpStatus.BAD_REQUEST)

        when (answerDTO) {
            is SingleAnsDTO -> {
                if (survey.surveyType != "SingleAnsSurvey") return ResponseEntity(HttpStatus.BAD_REQUEST)
                survey as SingleAnsSurvey

                val singleAnswer = SingleAnswer().also {
                    it.possibleAnswer = answerDTO.possibleAnswer
                    it.user = user
                }
                survey.singleAnswers!!.add(singleAnswer)
                surveyService.save(survey)
            }
            is MultyAnsDTO -> {
                if (survey.surveyType != "MultyAnsSurvey") return ResponseEntity(HttpStatus.BAD_REQUEST)
                survey as MultyAnsSurvey

                val multyAnswer = MultyAnswer().also {
                    it.possibleAnswers = answerDTO.possibleAnswers
                    it.user = user
                }
                survey.multyAnswers!!.add(multyAnswer)
                surveyService.save(survey)
            }
            is FreeAnsDTO -> {
                if (survey.surveyType != "FreeAnsSurvey") return ResponseEntity(HttpStatus.BAD_REQUEST)
                survey as FreeAnsSurvey

                val freeAnswer = FreeAnswer().also {
                    it.answer = answerDTO.answer
                    it.user = user
                }
                survey.freeAnswers!!.add(freeAnswer)
                surveyService.save(survey)
            }
            else -> {
                return ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }

        return ResponseEntity(HttpStatus.CREATED)
    }
}

