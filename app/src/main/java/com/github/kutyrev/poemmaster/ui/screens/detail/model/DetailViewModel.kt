package com.github.kutyrev.poemmaster.ui.screens.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.model.PoemWordVisualization
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import kotlinx.coroutines.launch

private const val SPACE = " "

class DetailViewModel(private val storageRepository: StorageRepository) :
    BaseViewModel<DetailEvent, DetailEffect>() {

    var state by mutableStateOf(DetailState())
        private set

    override fun handleEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.ChangeIsEditMode -> {
                state = state.copy(isEditMode = !state.isEditMode)
            }

            DetailEvent.ChangeHidePercentage -> {
                val hidePercent = changeHidePercentage(state.hidePercent)
                val poemWords = generateAnnotatedPoem(state.poemWords, hidePercent)
                state = state.copy(hidePercent = hidePercent, poemWords = poemWords)
            }

            is DetailEvent.AnnotatedWordClick -> {
                event.annotatedWord.isHided = !event.annotatedWord.isHided
            }
        }
    }

    fun loadPoem(poemId: Long) {
        viewModelScope.launch {
            val poem = storageRepository.getPoem(poemId)
            val poemWords: List<PoemWordVisualization> =
                poem.text.split(SPACE).map { PoemWordVisualization(it) }
            state =
                state.copy(poem = poem, poemWords = poemWords, isEditMode = poemWords.isNotEmpty())
        }
    }

    private fun changeHidePercentage(currentPercentage: Int): Int {
        return if (currentPercentage == 100) 0 else
            currentPercentage + 20
    }

    private fun generateAnnotatedPoem(
        poemWords: List<PoemWordVisualization>,
        hidePercent: Int
    ): List<PoemWordVisualization> {
        if (hidePercent == 0) {
            poemWords.forEach { it.isHided = false }
        }

        var wordsToHide = poemWords.size * hidePercent / 100
        val alreadyHided = poemWords.filter { it.isHided }.size
        wordsToHide -= alreadyHided

        repeat(wordsToHide) {
            poemWords.filter { it.isHided }.randomOrNull()?.isHided = true
        }

        return poemWords
    }
}