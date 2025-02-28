package com.github.kutyrev.poemmaster.ui.mainlist.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository

class MainListViewModel(
    private val storageRepository: StorageRepository
): BaseViewModel<MainListEvent>() {

    var state by mutableStateOf(MainListState())
        private set

    override fun handleEvent(event: MainListEvent) {
        when(event) {
            MainListEvent.GoToPoem -> TODO()
        }
    }
}