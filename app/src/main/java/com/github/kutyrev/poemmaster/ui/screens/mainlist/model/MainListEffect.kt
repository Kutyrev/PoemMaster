package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEffect

sealed interface MainListEffect : ViewEffect {

    data class ToPoem(
        val poemId: Long
    ) : MainListEffect
}