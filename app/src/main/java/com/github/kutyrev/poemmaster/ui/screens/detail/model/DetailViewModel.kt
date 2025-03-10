package com.github.kutyrev.poemmaster.ui.screens.detail.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.github.kutyrev.poemmaster.core.BaseViewModel
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository

class DetailViewModel(private val storageRepository: StorageRepository): BaseViewModel<DetailEvent, DetailEffect, DetailMode>() {

    var state by mutableStateOf(DetailState())
        private set

    override fun handleEvent(event: DetailEvent) {
        TODO("Not yet implemented")
    }

    fun setPoemId(poemId: Long) {
        TODO("Not yet implemented")
    }

    fun loadPoemText(poemId: Long) {
        TODO("Not yet implemented")
    }
}