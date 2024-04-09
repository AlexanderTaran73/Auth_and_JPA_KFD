package com.example.kotlinAuth.controllers


import com.example.kotlinAuth.models.PossibleAnswer
import com.example.kotlinAuth.services.controller_services.AdminContrService
import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import io.micrometer.observation.annotation.Observed
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@Observed(name = "AdminController")
@RequestMapping("/admin")
class AdminController(
    private val adminContrService: AdminContrService
) {

    @GetMapping("/getAllUsers")
    fun getAllUsers(): ResponseEntity<Any>{
        return adminContrService.getAllUsers()
    }

    @PostMapping("/surveys/createSurvey")
    fun createSurvey(@RequestBody surDTO: SurDTO): ResponseEntity<Any>{
        return adminContrService.createSurvey(surDTO)
    }

    @GetMapping("/surveys/getAllSurvey")
    fun getAllSurvey(): ResponseEntity<Any>{
        return adminContrService.getAllSurvey()
    }

    @GetMapping("/surveys/getSurveyResult")
    fun getSurveyResult(@RequestParam(required = true) id: Int, surveyType: String): ResponseEntity<Any>{
        return adminContrService.getSurveyResult(id, surveyType)
    }
}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "surType", visible = true)
@JsonSubTypes(
    JsonSubTypes.Type(value = SingleAnsSurDTO::class, name = "SingleAnsSur"),
    JsonSubTypes.Type(value = MultyAnsSurDTO::class, name = "MultyAnsSur"),
    JsonSubTypes.Type(value = FreeAnsSurDTO::class, name = "FreeAnsSur")
)
open class SurDTO(
    open val question: String,
    open val surType: String
)

class SingleAnsSurDTO(
    override val question: String,
    override val surType: String,
    val possibleAnswers: MutableList<PossibleAnswer>

): SurDTO(question, surType)

class MultyAnsSurDTO(
    override val question: String,
    override val surType: String,
    val possibleAnswers: MutableList<PossibleAnswer>

    ): SurDTO(question, surType)

class FreeAnsSurDTO(
    override val question: String,
    override val surType: String,
): SurDTO(question, surType)


class PossibleAnsDTO(
    val answer: String
)