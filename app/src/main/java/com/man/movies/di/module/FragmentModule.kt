package com.man.movies.di.module

import com.man.domain.usecase.movie.MoviesUseCase
import com.man.movies.data.source.MoviePagedListRepository
import com.man.movies.screen.dashboard.ui.nowplaying.NowPlayingViewModel
import dagger.Module
import dagger.Provides


@Module
class FragmentModule {

    @Provides
    fun providePagedListRepository(moviesUseCase: MoviesUseCase) =
        MoviePagedListRepository(moviesUseCase)
}