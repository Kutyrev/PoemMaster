package com.github.kutyrev.poemmaster.ui.screens.detail.model

import com.github.kutyrev.poemmaster.core.ViewEvent

sealed interface DetailEvent : ViewEvent {
    object changeIsEditMode: DetailEvent
    object changeHidePercentage: DetailEvent
}