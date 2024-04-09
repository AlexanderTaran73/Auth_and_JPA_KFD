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
import io.micrometer.observation.annotation.Observed
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils


@Service
@Observed(name = "UserContrService")
class UserContrService(
    private val surveyService: SurveyService,
    private val jwtProvider: JwtProvider,
    private val userService: UserService
) {



    fun getAllSurvey(): ResponseEntity<Any> {
        return ResponseEntity(surveyService.publicFindAll(), HttpStatus.OK)
    }

//    TODO учесть, что пользователя из keycloak ожет не быть в бд
    fun answerSurvey(answerDTO: AnswerDTO, request: HttpServletRequest): ResponseEntity<Any> {

        val bearer = request.getHeader(JwtFilter.AUTHORIZATION)
        val token = if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) { bearer.substring(7) } else null

        val userEmail = jwtProvider.getEmailFromToken(token)
        val user = userService.findByEmail(userEmail ?: "")

        val survey = surveyService.findByIdAndAnswerType(answerDTO.surveyId, answerDTO.answerType!!)
        if (survey == null || user == null) return ResponseEntity(HttpStatus.BAD_REQUEST)

        when (answerDTO) {
            is SingleAnsDTO -> {
                if (survey is SingleAnsSurvey) {
                    SingleAnswer().also {

                        if (!(isAnswerInPossibleAnswers(answerDTO.possibleAnswer, survey.possibleAnswers))) return ResponseEntity(HttpStatus.BAD_REQUEST)

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

                        answerDTO.possibleAnswers.forEach(){
                            if (!(isAnswerInPossibleAnswers(it, survey.possibleAnswers))) return ResponseEntity(HttpStatus.BAD_REQUEST)
                        }

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



    fun isAnswerInPossibleAnswers(possibleAnswer: PossibleAnswer, possibleAnswers: MutableList<PossibleAnswer>): Boolean{
        possibleAnswers.forEach(){
            if (it.answer == possibleAnswer.answer && it.id == possibleAnswer.id) return true
        }
        return false
    }
}

