package com.github.kutyrev.poemmaster.repository.storage

import com.github.kutyrev.poemmaster.datasource.database.PoemMasterDao
import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.model.PoemHeader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DefaultStorageRepository(
    private val poemMasterDao: PoemMasterDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : StorageRepository {

    override suspend fun getPoemsList(): Flow<List<PoemHeader>> =
        withContext(dispatcher) {
            poemMasterDao.getPoemsList()
        }

    override suspend fun addNewPoem(poem: Poem): Long =
        withContext(dispatcher) {
            poemMasterDao.insertPoem(poem)
        }

    override suspend fun getPoem(poemId: Long): Poem =
        withContext(dispatcher) {
            poemMasterDao.getPoem(poemId)
        }
}