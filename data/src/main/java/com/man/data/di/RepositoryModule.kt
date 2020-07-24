package com.man.data.di

import com.man.data.repository.movie.RepositoryMovieDataSource
import com.man.data.repository.movie.RepositoryMovieImpl
import com.man.data.utils.Const
import com.man.domain.repository.MovieDomainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(
        @Named(Const.LOCAL_DATASOURCE) localDataSource: RepositoryMovieDataSource,
        @Named(Const.REMOTE_DATASOURCE) remoteDataSource: RepositoryMovieDataSource
    ): MovieDomainRepository {
        return RepositoryMovieImpl(localDataSource, remoteDataSource)
    }

}