package com.github.kutyrev.poemmaster.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kutyrev.poemmaster.model.Poem

@Database(entities = [Poem::class], version = 1)
abstract class DatabaseSource : RoomDatabase() {
    abstract fun poemMasterDao(): PoemMasterDao
}