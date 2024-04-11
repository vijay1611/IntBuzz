package com.example.intbuzz

data class Option(
    val isCorrect: Boolean,
    val option: String,
    var isSelected : Boolean? = null
)