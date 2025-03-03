package com.github.kutyrev.poemmaster.ui.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEvent

sealed interface MainListEvent : ViewEvent {
    data object GoToPoem: MainListEvent
    data object AddNewPoem: MainListEvent
}