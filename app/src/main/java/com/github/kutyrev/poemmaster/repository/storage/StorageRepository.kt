package com.github.kutyrev.poemmaster.repository.storage

import com.github.kutyrev.poemmaster.model.PoemHeader
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    fun getPoemsList(): Flow<List<PoemHeader>>
}