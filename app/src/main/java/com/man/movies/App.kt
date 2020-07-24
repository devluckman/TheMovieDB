package com.man.movies

import android.app.Application
import com.man.data.di.ApiModule
import com.man.data.di.DataSourceModule
import com.man.data.di.RepositoryModule
import com.man.domain.di.UseCaseModule
import com.man.movies.di.component.ApplicationComponent
import com.man.movies.di.component.DaggerApplicationComponent
import com.man.movies.di.module.ApplicationModule

class App  : Application()  {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .apiModule(ApiModule())
            .dataSourceModule(DataSourceModule())
            .repositoryModule(RepositoryModule())
            .useCaseModule(UseCaseModule())
            .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return applicationComponent
    }
}