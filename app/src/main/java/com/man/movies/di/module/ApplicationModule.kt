package com.man.movies.di.module

import android.content.Context
import com.man.movies.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val application: App) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application.applicationContext
    }

}