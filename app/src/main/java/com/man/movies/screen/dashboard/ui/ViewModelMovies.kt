package com.man.movies.screen.dashboard.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import com.man.domain.model.movie.MovieItemsModel
import com.man.movies.data.NetworkState
import com.man.movies.data.TypeMovie
import com.man.movies.data.source.MoviePagedListRepository
import io.reactivex.disposables.CompositeDisposable

class ViewModelMovies(
    repository: MoviePagedListRepository,
    typeMovie: TypeMovie
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val moviePagedList: LiveData<PagedList<MovieItemsModel>> by lazy {
        repository.fetchingMovieList(compositeDisposable, typeMovie)
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

    fun getFilteredList(s: String): LiveData<List<MovieItemsModel>> {
        return Transformations.map(moviePagedList) {
            if (s.isNotEmpty()) it.filter { movies -> movies.title.contains(s, true) }
            else it
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val repository: MoviePagedListRepository, private val typeMovie: TypeMovie) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ViewModelMovies(
                repository,
                typeMovie
            ) as T
        }
    }
}