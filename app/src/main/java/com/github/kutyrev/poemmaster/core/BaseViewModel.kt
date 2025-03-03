package com.github.kutyrev.poemmaster.core

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<Event: ViewEvent>: ViewModel() {

    abstract fun handleEvent(event: Event)
}

interface ViewEvent
