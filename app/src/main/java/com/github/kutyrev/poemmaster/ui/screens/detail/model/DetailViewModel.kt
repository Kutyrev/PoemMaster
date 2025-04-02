package com.github.kutyrev.poemmaster.ui.screens.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.viewModelScope
import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.model.PoemWordVisualization
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import kotlinx.coroutines.launch

const val SPACE = " "

class DetailViewModel(private val storageRepository: StorageRepository) :
    BaseViewModel<DetailEvent, DetailEffect>() {

    var state by mutableStateOf(DetailState())
        private set

    override fun handleEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.ChangeIsEditMode -> {
                val newIsEditMode = !state.isEditMode

                if (newIsEditMode == false) {
                    val poemWords: List<PoemWordVisualization> =
                        state.poemText.split(SPACE).map { PoemWordVisualization(it) }
                    state.poem.text = state.poemText
                    state.poem.name = state.poemName

                    state = state.copy(isEditMode = false)
                    state.poemWords.clear()
                    state.poemWords.addAll(poemWords)

                    viewModelScope.launch {
                        storageRepository.updatePoem(state.poem)
                    }
                } else {
                    state = state.copy(
                        isEditMode = true,
                        numberOfOpenedWords = 0,
                        hidePercent = 0
                    )
                }
            }

            DetailEvent.ChangeHidePercentage -> {
                val hidePercent = changeHidePercentage(state.hidePercent)
                generateAnnotatedPoem(state.poemWords, hidePercent)
                state = state.copy(
                    hidePercent = hidePercent,
                    numberOfOpenedWords = 0
                )
            }

            is DetailEvent.AnnotatedWordClick -> {
                event.annotatedWord.isHided = !event.annotatedWord.isHided
                val elementIndex = state.poemWords.indexOf(event.annotatedWord)
                state.poemWords.remove(event.annotatedWord)
                state.poemWords.add(elementIndex, event.annotatedWord.copy())

                if (!event.annotatedWord.isHided) {
                    state = state.copy(numberOfOpenedWords = state.numberOfOpenedWords.inc())
                }
            }

            is DetailEvent.ChangePoemName -> {
                state = state.copy(poemName = event.name)
            }

            is DetailEvent.ChangePoemText -> {
                state = state.copy(poemText = event.text)
            }
        }
    }

    fun loadPoem(poemId: Long) {
        viewModelScope.launch {
            val poem = storageRepository.getPoem(poemId)
            val poemWords: List<PoemWordVisualization> =
                poem.text.split(SPACE).filter { it.isNotEmpty() }.map { PoemWordVisualization(it) }
            state =
                state.copy(
                    poem = poem,
                    poemName = poem.name,
                    poemText = poem.text,
                    isEditMode = poemWords.isEmpty()
                )
            state.poemWords.clear()
            state.poemWords.addAll(poemWords)
        }
    }

    private fun changeHidePercentage(currentPercentage: Int): Int {
        return if (currentPercentage == 100) 0 else
            currentPercentage + 20
    }

    private fun generateAnnotatedPoem(
        poemWords: SnapshotStateList<PoemWordVisualization>,
        hidePercent: Int
    ) {
        val newPoemWords = mutableListOf<PoemWordVisualization>()
        newPoemWords.addAll(poemWords)

        if (hidePercent == 0) {
            newPoemWords.forEach { it.isHided = false }
        }

        var wordsToHide = newPoemWords.size * hidePercent / 100
        val alreadyHided = newPoemWords.filter { it.isHided }.size
        wordsToHide -= alreadyHided

        repeat(wordsToHide) {
            newPoemWords.filter { !it.isHided }.randomOrNull()?.isHided = true
        }

        poemWords.clear()
        poemWords.addAll(newPoemWords)
    }
}