package com.github.kutyrev.poemmaster.ui.mainlist.model

sealed interface MainListEffect {

    data class ToPoem(
        val poemId: Long
    ) : MainListEffect
}