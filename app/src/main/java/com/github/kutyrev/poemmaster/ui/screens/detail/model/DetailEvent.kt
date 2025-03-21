package com.github.kutyrev.poemmaster.ui.screens.detail.model

import com.github.kutyrev.poemmaster.core.ViewEvent
import com.github.kutyrev.poemmaster.model.PoemWordVisualization

sealed interface DetailEvent : ViewEvent {
    object ChangeIsEditMode : DetailEvent
    object ChangeHidePercentage : DetailEvent
    class AnnotatedWordClick(val annotatedWord: PoemWordVisualization) : DetailEvent
    class ChangePoemName(val name: String) : DetailEvent
}