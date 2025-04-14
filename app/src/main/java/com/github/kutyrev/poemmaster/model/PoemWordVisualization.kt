package com.github.kutyrev.poemmaster.model

data class PoemWordVisualization(
    val word: String,
    var isHided: Boolean = false,
    val delimeter: String = " "
)