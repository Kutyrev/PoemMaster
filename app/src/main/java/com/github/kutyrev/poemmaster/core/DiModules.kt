package com.github.kutyrev.poemmaster.core

import androidx.room.Room
import com.github.kutyrev.poemmaster.datasource.database.DatabaseSource
import com.github.kutyrev.poemmaster.repository.storage.DefaultStorageRepository
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), DatabaseSource::class.java, "poem_database").build()
    }

    single { get<DatabaseSource>().poemMasterDao() }
}

val repositoryModule = module {
    singleOf(::DefaultStorageRepository) { bind<StorageRepository>() }
}

val appModule = module {
    viewModel { MainListViewModel(get()) }
}