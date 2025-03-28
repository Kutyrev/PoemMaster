package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import com.github.kutyrev.poemmaster.core.ViewEffect
import com.github.kutyrev.poemmaster.model.PoemHeader

sealed interface MainListEffect : ViewEffect {

    data class ToPoem(
        val poemId: Long
    ) : MainListEffect
    class ShowSwipeToDeleteConfirmSnackbar(val poemHeader: PoemHeader) : MainListEffect
}