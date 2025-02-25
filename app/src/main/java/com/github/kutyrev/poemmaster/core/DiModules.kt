package com.github.kutyrev.poemmaster.core

import androidx.room.Room
import com.github.kutyrev.poemmaster.datasource.database.DatabaseSource
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), DatabaseSource::class.java, "poem_database").build()
    }

    single { get<DatabaseSource>().poemMasterDao() }
}