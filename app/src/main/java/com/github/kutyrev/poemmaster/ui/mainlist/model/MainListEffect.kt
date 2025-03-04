package com.github.kutyrev.poemmaster.ui.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEffect

sealed interface MainListEffect : ViewEffect {

    data class ToPoem(
        val poemId: Long
    ) : MainListEffect
}