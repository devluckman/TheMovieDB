package com.man.data.remote.model.movies

import com.google.gson.annotations.SerializedName
import com.man.data.extentions.replaceIfNull
import com.man.domain.model.movie.MovieItemsModel

data class MovieItems(
	@SerializedName("overview")
	val overview: String?,

	@SerializedName("original_language")
	val originalLanguage: String?,

	@SerializedName("original_title")
	val originalTitle: String?,

	@SerializedName("video")
	val video: Boolean?,

	@SerializedName("title")
	val title: String?,

	@SerializedName("genre_ids")
	val genreIds: List<Int>?,

	@SerializedName("poster_path")
	val posterPath: String?,

	@SerializedName("release_date")
	val releaseDate: String?,

	@SerializedName("popularity")
	val popularity: Float?,

	@SerializedName("vote_average")
	val voteAverage: Float?,

	@SerializedName("id")
	val id: Int,

	@SerializedName("adult")
	val adult: Boolean?,

	@SerializedName("vote_count")
	val voteCount: Int?
) {
	companion object {
		fun transform (response : MovieItems) : MovieItemsModel {
			return MovieItemsModel(
				overview = response.overview.replaceIfNull(),
				originalLanguage = response.originalLanguage.replaceIfNull(),
				originalTitle = response.originalTitle.replaceIfNull(),
				video = response.video.replaceIfNull(),
				title = response.title.replaceIfNull(),
				genreIds = response.genreIds ?: arrayListOf() ,
				posterPath = response.posterPath.replaceIfNull("7S9RvfMBWSTlUZ4VbxDY7oLeenk.jpg"),
				releaseDate = response.releaseDate.replaceIfNull(),
				popularity = response.popularity.replaceIfNull(),
				voteAverage = response.voteAverage.replaceIfNull(),
				id = response.id,
				adult = response.adult.replaceIfNull(),
				voteCount = response.voteCount.replaceIfNull()
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