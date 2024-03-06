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

    fun answerSurvey(answerDTO: AnswerDTO, request: HttpServletRequest): ResponseEntity<Any> {  //TODO: добавить проверку на принадлежность ответа к определенному опросу
        val bearer = request.getHeader(JwtFilter.AUTHORIZATION)
        val token = if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) { bearer.substring(7) } else null
        val user = userService.findByEmail(jwtProvider.getEmailFromToken(token))

        val survey = surveyService.findByIdAndAnswerType(answerDTO.surveyId, answerDTO.answerType!!)
        if (survey == null || user == null) return ResponseEntity(HttpStatus.BAD_REQUEST)

        when (answerDTO) {
            is SingleAnsDTO -> {
                if (survey is SingleAnsSurvey) {
                    SingleAnswer().also {
                        it.usersId = user.id
                        it.possibleAnswer = answerDTO.possibleAnswer
                        survey.singleAnswers!!.add(it)
                        surveyService.save(survey)
                        return ResponseEntity(HttpStatus.CREATED)
                    }
                }

            }
            is MultyAnsDTO -> {
                if (survey is MultyAnsSurvey) {
                    MultyAnswer().also {
                        it.usersId = user.id
                        it.possibleAnswers = answerDTO.possibleAnswers
                        survey.multyAnswers!!.add(it)
                        surveyService.save(survey)
                        return ResponseEntity(HttpStatus.CREATED)
                    }
                }

            }
            is FreeAnsDTO -> {
                if (survey is FreeAnsSurvey) {
                    FreeAnswer().also {
                        it.usersId = user.id
                        it.answer = answerDTO.answer
                        survey.freeAnswers!!.add(it)
                        surveyService.save(survey)
                        return ResponseEntity(HttpStatus.CREATED)
                    }
                }

            }
            else -> {
                return ResponseEntity(HttpStatus.BAD_REQUEST)
            }
        }

        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }
}

