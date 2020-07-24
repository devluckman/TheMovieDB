package com.man.data.repository.movie

import com.man.data.local.entity.movie.MovieEntity
import com.man.data.remote.model.movies.MoviesResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import java.util.*

interface RepositoryMovieDataSource {

    fun getMoviePopular(
        page : Int
    ): Observable<MoviesResponse>

    fun getMovieUpComing(
        page : Int
    ): Observable<MoviesResponse>

    fun getMovieNowPlaying(
        page : Int
    ): Observable<MoviesResponse>

    fun saveMovie(entity : MovieEntity) : Completable

    fun getMovieFavorite() : Flowable<List<MovieEntity>>

}