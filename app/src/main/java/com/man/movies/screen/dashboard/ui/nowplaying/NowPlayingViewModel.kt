package com.man.movies.screen.dashboard.ui.nowplaying

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import com.man.movies.data.source.MoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class NowPlayingViewModel (
    repository: MoviePagedListRepository
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<MovieItemsModel>> by lazy {
        repository.fetchingMovieList(compositeDisposable, TypeMovie.NOWPLAYING)
    }

    val networkState: LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun movieListIsEmpty(): Boolean {
        return moviePagedList.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    class Factory(private val repository: MoviePagedListRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NowPlayingViewModel(repository) as T
        }
    }
}