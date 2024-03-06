package com.example.kotlinAuth.models

import jakarta.persistence.*



@MappedSuperclass
abstract class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column
    var question: String? = null

}


@Entity
class SingleAnsSurvey: Survey() {

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "single_ans_survey_id")
    var possibleAnswers: MutableList<PossibleAnswer> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "single_ans_survey_id")
    var singleAnswers: MutableList<SingleAnswer>? = mutableListOf()

}

@Entity
class MultyAnsSurvey: Survey(){

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "multy_ans_survey_id")
    var possibleAnswers: MutableList<PossibleAnswer> = mutableListOf()

    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "multy_ans_survey_id")
    var multyAnswers: MutableList<MultyAnswer>? = mutableListOf()
}


@Entity
class FreeAnsSurvey: Survey(){
    @OneToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = " free_ans_survey_id")
    var freeAnswers: MutableList<FreeAnswer>? = mutableListOf()
}

