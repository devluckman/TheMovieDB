package com.man.movies.data.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.man.domain.model.movie.MovieItemsModel
import com.man.domain.usecase.movie.MoviesUseCase
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieDataSource(
    private val moviesUseCase: MoviesUseCase,
    private val compositeDisposable: CompositeDisposable,
    private val typeMovie: TypeMovie
) : PageKeyedDataSource<Int, MovieItemsModel>() {

    private val FIRST_PAGE = 1
    val networkState: MutableLiveData<NetworkState> = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MovieItemsModel>
    ) {
        networkState.postValue(NetworkState.LOADING)
        val disposable = when (typeMovie) {
            TypeMovie.POPULAR -> moviesUseCase.getMoviePopular(FIRST_PAGE)
            TypeMovie.NOWPLAYING -> moviesUseCase.getMovieNowPlaying(FIRST_PAGE)
            TypeMovie.UPCOMING -> moviesUseCase.getMovieUpComing(FIRST_PAGE)
        }
        compositeDisposable.add(
            disposable
                .subscribeOn(Schedulers.io())
                .subscribe({
                    callback.onResult(
                        it.results,
                        null,
                        FIRST_PAGE + 1
                    )
                    networkState.postValue(NetworkState.LOADED)
                }, {
                    networkState.postValue(NetworkState.ERROR)
                })
        )
    }

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieItemsModel>
    ) {
        val disposable = when (typeMovie) {
            TypeMovie.POPULAR -> moviesUseCase.getMoviePopular(params.key)
            TypeMovie.NOWPLAYING -> moviesUseCase.getMovieNowPlaying(params.key)
            TypeMovie.UPCOMING -> moviesUseCase.getMovieUpComing(params.key)
        }

        compositeDisposable.add(
            disposable.subscribeOn(Schedulers.io()).subscribe({
                if (it.totalPages >= params.key) {
                    callback.onResult(it.results, params.key + 1)
                    networkState.postValue(NetworkState.LOADED)
                } else {
                    networkState.postValue(NetworkState.ENDOFLIST)
                }
            }, {
                networkState.postValue(NetworkState.ERROR)
            })
        )
    }

    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, MovieItemsModel>
    ) {}


}