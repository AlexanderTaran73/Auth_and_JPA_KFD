package com.example.kotlinAuth.controllers


import com.example.kotlinAuth.models.PossibleAnswer
import com.example.kotlinAuth.services.controller_services.AdminContrService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/admin")
class AdminController(
    private val adminContrService: AdminContrService
) {

    @GetMapping("/getAllUsers")
    fun getAllUsers(): ResponseEntity<Any>{
        return adminContrService.getAllUsers()
    }
//    TODO: Объеденить создания опросов в одну функцию
    @PostMapping("/surveys/createSurvey/singleAns")
    fun createSingleAnsSurvey(@RequestBody surDTO: SingleAnsSurDTO): ResponseEntity<Any>{
        return adminContrService.createSingleAnsSurvey(surDTO)
    }

    @PostMapping("/surveys/createSurvey/multyAns")
    fun createMultyAnsSurvey(@RequestBody surDTO: MultyAnsSurDTO): ResponseEntity<Any>{
        return adminContrService.createMultyAnsSurvey(surDTO)
    }

    @PostMapping("/surveys/createSurvey/freeAns")
    fun createFreeAnsSurvey(@RequestBody surDTO: FreeAnsSurDTO): ResponseEntity<Any>{
        return adminContrService.createFreeAnsSurvey(surDTO)
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

class SingleAnsSurDTO(
    val question: String,
    val possibleAnswers: MutableList<PossibleAnswer>

)

class MultyAnsSurDTO(
    val question: String,
    val possibleAnswers: MutableList<PossibleAnswer>

    )

class FreeAnsSurDTO(
    val question: String
)


class PossibleAnsDTO(
    val answer: String
)