package com.github.kutyrev.poemmaster.datasource.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.model.PoemHeader
import kotlinx.coroutines.flow.Flow

@Dao
interface PoemMasterDao {
    @Query("SELECT id, name FROM poems")
    fun getPoemsList(): Flow<List<PoemHeader>>

    @Query("SELECT * FROM poems WHERE id = :id")
    fun getPoem(id: Long): Poem

    @Insert
    fun insertPoem(poem: Poem): Long

    @Update
    fun updatePoem(poem: Poem)

    @Delete
    fun deletePoem(poem: Poem)
}
