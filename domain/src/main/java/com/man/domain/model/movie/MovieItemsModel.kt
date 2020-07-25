package com.man.domain.model.movie

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItemsModel(
	val overview: String,
	val originalLanguage: String,
	val originalTitle: String,
	val video: Boolean,
	val title: String,
	val genreIds: List<Int>,
	val posterPath: String,
	val releaseDate: String,
	val popularity: Float,
	val voteAverage: Float,
	val id: Int,
	val adult: Boolean,
	val voteCount: Int
) : Parcelable
