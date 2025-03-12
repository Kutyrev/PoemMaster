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
        state = when(event) {
            DetailEvent.changeIsEditMode -> {
                state.copy(isEditMode = !state.isEditMode)
            }

            DetailEvent.changeHidePercentage -> {
                state.copy(hidePercent = changeHidePercentage(state.hidePercent))
            }
        }
    }

    fun loadPoem(poemId: Long) {
        viewModelScope.launch {
            state = state.copy(poem = storageRepository.getPoem(poemId))
        }
    }

    private fun changeHidePercentage(currentPercentage: Int): Int {
        return if (currentPercentage == 100) 0 else
            currentPercentage + 20
    }

    private fun generateAnnotatedPoem() {

    }
}