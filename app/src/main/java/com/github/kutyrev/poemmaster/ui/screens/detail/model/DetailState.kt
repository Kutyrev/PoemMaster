package com.github.kutyrev.poemmaster.ui.screens.detail.model

import com.github.kutyrev.poemmaster.model.Poem

data class DetailState(
    val poem: Poem = Poem(),
    val isEditMode: Boolean = false,
    val hidePercent: Int = 0
)