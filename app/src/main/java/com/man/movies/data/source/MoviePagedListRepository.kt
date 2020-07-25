package com.man.movies.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.man.domain.model.movie.MovieItemsModel
import com.man.domain.usecase.movie.MoviesUseCase
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository (private val moviesUseCase: MoviesUseCase) {

    private lateinit var movieDataSourceFactory: MovieDataSourceFactory
    private var PAGE_SIZE = 10

    fun fetchingMovieList(
        compositeDisposable: CompositeDisposable,
        typeMovie: TypeMovie
    ) : LiveData<PagedList<MovieItemsModel>> {
        movieDataSourceFactory = MovieDataSourceFactory(moviesUseCase, compositeDisposable, typeMovie)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(PAGE_SIZE)
            .setPageSize(PAGE_SIZE)
            .build()

        return LivePagedListBuilder(movieDataSourceFactory, config).build()
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap(
            movieDataSourceFactory.movieDataSourceLiveData, MovieDataSource::networkState
        )
    }
}