package com.man.movies.screen.dashboard.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.man.domain.usecase.movie.MoviesUseCase

class UpComingViewModel(
    private val moviesUseCase: MoviesUseCase
) : ViewModel() {




    class Factory(private val moviesUseCase: MoviesUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return UpComingViewModel(moviesUseCase) as T
        }
    }
}