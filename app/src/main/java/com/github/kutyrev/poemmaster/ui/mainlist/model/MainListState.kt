package com.github.kutyrev.poemmaster.ui.mainlist.model

import com.github.kutyrev.poemmaster.model.PoemHeader

data class MainListState(
    val poemsList: List<PoemHeader> = listOf()
) {
}