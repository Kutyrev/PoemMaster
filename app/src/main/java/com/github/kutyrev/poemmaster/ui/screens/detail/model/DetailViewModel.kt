package com.github.kutyrev.poemmaster.ui.screens.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val storageRepository: StorageRepository): BaseViewModel<DetailEvent, DetailEffect>() {

    var state by mutableStateOf(DetailState())
        private set

    override fun handleEvent(event: DetailEvent) {
        TODO("Not yet implemented")
    }

    fun loadPoem(poemId: Long) {
        viewModelScope.launch {
            state = state.copy(poem = storageRepository.getPoem(poemId))
        }
    }
}