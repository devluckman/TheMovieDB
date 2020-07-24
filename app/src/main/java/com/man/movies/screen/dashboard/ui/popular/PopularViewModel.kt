package com.man.movies.screen.dashboard.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.man.domain.usecase.movie.MoviesUseCase

class PopularViewModel(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {




    class Factory(private val moviesUseCase: MoviesUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return PopularViewModel(moviesUseCase) as T
        }
    }
}