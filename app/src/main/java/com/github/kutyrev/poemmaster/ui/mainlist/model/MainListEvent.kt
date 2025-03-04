package com.github.kutyrev.poemmaster.ui.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEvent

sealed interface MainListEvent : ViewEvent {
    data class GoToPoem(val poemId: Long): MainListEvent
    data object AddNewPoem: MainListEvent
}