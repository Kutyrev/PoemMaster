package com.github.kutyrev.poemmaster.ui.screens.mainlist.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.github.kutyrev.poemmaster.model.PoemHeader

data class MainListState(
    val poemsList: SnapshotStateList<PoemHeader> = mutableStateListOf<PoemHeader>()
)