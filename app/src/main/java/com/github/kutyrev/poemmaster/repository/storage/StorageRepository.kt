package com.github.kutyrev.poemmaster.repository.storage

import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.model.PoemHeader
import kotlinx.coroutines.flow.Flow

interface StorageRepository {
    suspend fun getPoemsList(): Flow<List<PoemHeader>>
    suspend fun addNewPoem(poem: Poem): Long
    suspend fun getPoem(poemId: Long): Poem
}