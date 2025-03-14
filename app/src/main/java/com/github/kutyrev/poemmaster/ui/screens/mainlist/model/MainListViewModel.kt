package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MainListViewModel(
    private val storageRepository: StorageRepository
) : BaseViewModel<MainListEvent, MainListEffect>() {

    var state by mutableStateOf(MainListState())
        private set

    init {
        loadPoemsList()
    }

    override fun handleEvent(event: MainListEvent) {
        when (event) {
            MainListEvent.AddNewPoem -> viewModelScope.launch {
                addNewPoem()
            }

            is MainListEvent.GoToPoem -> viewModelScope.launch {
                emitNewEffect(MainListEffect.ToPoem(event.poemId))
            }
        }
    }

    private fun loadPoemsList() {
        viewModelScope.launch {
            storageRepository.getPoemsList()
                .catch { }
                .collect { poems ->
                    state = state.copy(poemsList = poems)
                }
        }
    }

    private fun addNewPoem() {
        viewModelScope.launch {
            val newPoemId = storageRepository.addNewPoem(Poem())
            emitNewEffect(MainListEffect.ToPoem(newPoemId))
        }
    }
}