package com.example.kotlinAuth.services

import com.example.kotlinAuth.models.FreeAnsSurvey
import com.example.kotlinAuth.models.MultyAnsSurvey
import com.example.kotlinAuth.models.SingleAnsSurvey
import com.example.kotlinAuth.models.Survey
import com.example.kotlinAuth.repositories.FreeAnsSurveyRepository
import com.example.kotlinAuth.repositories.MultyAnsSurveyRepository
import com.example.kotlinAuth.repositories.SingleAnsSurveyRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional
class SurveyServiceImpl(
    private val singleAnsSurveyRepository: SingleAnsSurveyRepository,
    private val multyAnsSurveyRepository: MultyAnsSurveyRepository,
    private val freeAnsSurveyRepository: FreeAnsSurveyRepository
): SurveyService{

    override fun findAll(): MutableList<Survey> {
        var allSurvey = mutableListOf<Survey>()
        allSurvey.addAll(singleAnsSurveyRepository.findAll())
        allSurvey.addAll(multyAnsSurveyRepository.findAll())
        allSurvey.addAll(freeAnsSurveyRepository.findAll())
        return allSurvey
    }

    override fun publicFindAll(): MutableList<Survey> {
        var allSurvey = mutableListOf<Survey>()
        singleAnsSurveyRepository.findAll().forEach{
            it.singleAnswers = null
            allSurvey.add(it)
        }
        multyAnsSurveyRepository.findAll().forEach{
            it.multyAnswers = null
            allSurvey.add(it)
        }
        freeAnsSurveyRepository.findAll().forEach{
            it.freeAnswers = null
            allSurvey.add(it)
        }
        return allSurvey
    }

    override fun save(singleAnsSurvey: SingleAnsSurvey) {
        singleAnsSurveyRepository.save(singleAnsSurvey)
    }

    override fun save(multyAnsSurvey: MultyAnsSurvey) {
        multyAnsSurveyRepository.save(multyAnsSurvey)
    }

    override fun save(freeAnsSurvey: FreeAnsSurvey) {
        freeAnsSurveyRepository.save(freeAnsSurvey)
    }

    override fun findByIdAndType(id: Int, surveyType: String): Survey? {
        when(surveyType){
            "SingleAnsSurvey" -> return singleAnsSurveyRepository.findById(id).orElse(null)
            "MultyAnsSurvey" -> return multyAnsSurveyRepository.findById(id).orElse(null)
            "FreeAnsSurvey" -> return freeAnsSurveyRepository.findById(id).orElse(null)
        }
        return null

    }

    override fun findByIdAndAnswerType(surveyId: Int, answerType: String): Survey? {
        when(answerType){
            "SingleAns" -> return singleAnsSurveyRepository.findById(surveyId).orElse(null)
            "MultyAns" -> return multyAnsSurveyRepository.findById(surveyId).orElse(null)
            "FreeAns" -> return freeAnsSurveyRepository.findById(surveyId).orElse(null)
        }
        return null
    }
}

