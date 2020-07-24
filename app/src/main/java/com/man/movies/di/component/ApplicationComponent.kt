package com.man.movies.di.component

import com.man.data.di.ApiModule
import com.man.data.di.DataSourceModule
import com.man.data.di.RepositoryModule
import com.man.domain.di.UseCaseModule
import com.man.domain.usecase.movie.MoviesUseCase
import com.man.movies.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        ApiModule::class,
        DataSourceModule::class,
        RepositoryModule::class,
        UseCaseModule::class
    ]
)
interface ApplicationComponent {

    fun moviesUseCase() : MoviesUseCase
}