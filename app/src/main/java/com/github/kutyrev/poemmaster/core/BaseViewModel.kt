package com.github.kutyrev.poemmaster.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<Event: ViewEvent, Effect: ViewEffect, Mode: ViewMode>: ViewModel() {

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    abstract fun handleEvent(event: Event)

    suspend fun emitNewEffect(effect: Effect) {
        _effect.send(effect)
    }
}

interface ViewEvent
interface ViewEffect
interface ViewMode
