package com.github.kutyrev.poemmaster.ui.screens.detail.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.model.PoemWordVisualization

data class DetailState(
    val poem: Poem = Poem(),
    val isEditMode: Boolean = false,
    val hidePercent: Int = 0,
    val poemName: String = "",
    val poemText: String = "",
    val numberOfOpenedWords: Int = 0,
    val poemWords: SnapshotStateList<PoemWordVisualization> = mutableStateListOf<PoemWordVisualization>()
)