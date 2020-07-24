package com.man.data.remote.model.movies

import com.google.gson.annotations.SerializedName
import com.man.domain.model.movie.MovieItemsModel

data class MovieItems(
	@SerializedName("overview")
	val overview: String,

	@SerializedName("original_language")
	val originalLanguage: String,

	@SerializedName("original_title")
	val originalTitle: String,

	@SerializedName("video")
	val video: Boolean,

	@SerializedName("title")
	val title: String,

	@SerializedName("genre_ids")
	val genreIds: List<Int>,

	@SerializedName("poster_path")
	val posterPath: String,

	@SerializedName("backdrop_path")
	val backdropPath: String,

	@SerializedName("release_date")
	val releaseDate: String,

	@SerializedName("popularity")
	val popularity: Double,

	@SerializedName("vote_average")
	val voteAverage: Int,

	@SerializedName("id")
	val id: Int,

	@SerializedName("adult")
	val adult: Boolean,

	@SerializedName("vote_count")
	val voteCount: Int
) {
	companion object {
		fun transform (response : MovieItems) : MovieItemsModel {
			return MovieItemsModel(
				overview = response.overview,
				originalLanguage = response.originalLanguage,
				originalTitle = response.originalTitle,
				video = response.video,
				title = response.title,
				genreIds = response.genreIds,
				posterPath = response.posterPath,
				backdropPath = response.backdropPath,
				releaseDate = response.releaseDate,
				popularity = response.popularity,
				voteAverage = response.voteAverage,
				id = response.id,
				adult = response.adult,
				voteCount = response.voteCount
			)
		}

		fun transform(response : List<MovieItems>) : List<MovieItemsModel>{
			val movies = arrayListOf<MovieItemsModel>()
			response.forEach {
				movies.add(transform(it))
			}
			return movies
		}
	}
}