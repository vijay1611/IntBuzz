package com.example.intbuzz

data class Question(
    val options: List<Option>,
    val question: String,
    var isCorrect: Boolean? = null
)