package com.github.kutyrev.poemmaster.core

import androidx.room.Room
import com.github.kutyrev.poemmaster.datasource.database.DatabaseSource
import com.github.kutyrev.poemmaster.repository.storage.DefaultStorageRepository
import com.github.kutyrev.poemmaster.repository.storage.StorageRepository
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailViewModel
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(get(), DatabaseSource::class.java, "poem_database").build()
    }

    single { get<DatabaseSource>().poemMasterDao() }
}

val dispatchersModule = module {
    single(named("IODispatcher")) {
        Dispatchers.IO
    }
}

val repositoryModule = module {
    single { DefaultStorageRepository(get(), get(named("IODispatcher"))) } bind StorageRepository::class
}

val appModule = module {
    viewModel { MainListViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}