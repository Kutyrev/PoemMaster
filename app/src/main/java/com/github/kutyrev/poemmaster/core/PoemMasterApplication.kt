package com.github.kutyrev.poemmaster.core

import android.app.Application
import org.koin.core.context.startKoin

class PoemMasterApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(databaseModule)
        }
    }
}