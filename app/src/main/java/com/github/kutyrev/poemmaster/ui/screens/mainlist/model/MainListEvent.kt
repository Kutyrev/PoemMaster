package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEvent

sealed interface MainListEvent : ViewEvent {
    class GoToPoem(val poemId: Long) : MainListEvent
    object AddNewPoem : MainListEvent
}