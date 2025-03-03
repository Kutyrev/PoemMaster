package com.github.kutyrev.poemmaster.repository.storage

import com.github.kutyrev.poemmaster.datasource.database.PoemMasterDao
import com.github.kutyrev.poemmaster.model.PoemHeader
import kotlinx.coroutines.flow.Flow

class DefaultStorageRepository(private val poemMasterDao: PoemMasterDao) : StorageRepository {
    override fun getPoemsList(): Flow<List<PoemHeader>> {
        return poemMasterDao.getPoemsList()
    }

    override fun addNewPoem() : Long {
        return poemMasterDao.insertPoem()
    }
}