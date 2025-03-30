package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEvent
import com.github.kutyrev.poemmaster.model.PoemHeader

sealed interface MainListEvent : ViewEvent {
    class GoToPoem(val poemId: Long) : MainListEvent
    object AddNewPoem : MainListEvent
    class DeleteSwipePerformed(val poemHeader: PoemHeader): MainListEvent
    class DeleteSnackbarActionPerformed(val poemHeader: PoemHeader): MainListEvent
}