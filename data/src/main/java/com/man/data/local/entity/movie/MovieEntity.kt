package com.man.data.local.entity.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.man.data.local.database.Converters
import com.man.domain.model.movie.MovieItemsModel

@Entity(tableName =  "table_movies")
class MovieEntity (
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name =  "id") val id: Int,
    @ColumnInfo(name = "overview") val overview: String,
    @ColumnInfo(name = "original_language") val originalLanguage: String,
    @ColumnInfo(name = "original_title") val originalTitle: String,
    @ColumnInfo(name = "video") val video: Boolean,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val posterPath: String,
    @ColumnInfo(name = "release_date") val releaseDate: String,
    @ColumnInfo(name = "popularity") val popularity: Float,
    @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @ColumnInfo(name = "adult") val adult: Boolean,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @TypeConverters(Converters::class) @ColumnInfo(name = "genre_ids") val genreIds: List<Int>
) {
    companion object {
        fun transform (entity: MovieEntity) : MovieItemsModel {
            return MovieItemsModel(
                id = entity.id,
                overview = entity.overview,
                originalLanguage = entity.originalLanguage,
                originalTitle = entity.originalTitle,
                video = entity.video,
                title = entity.title,
                genreIds = entity.genreIds,
                posterPath = entity.posterPath,
                releaseDate = entity.releaseDate,
                popularity = entity.popularity,
                voteAverage = entity.voteAverage,
                adult = entity.adult,
                voteCount = entity.voteCount
            )
        }

        fun transform (model: MovieItemsModel) : MovieEntity {
            return MovieEntity(
                id = model.id,
                overview = model.overview,
                originalLanguage = model.originalLanguage,
                originalTitle = model.originalTitle,
                video = model.video,
                title = model.title,
                genreIds = model.genreIds,
                posterPath = model.posterPath,
                releaseDate = model.releaseDate,
                popularity = model.popularity,
                voteAverage = model.voteAverage,
                adult = model.adult,
                voteCount = model.voteCount
            )
        }

        fun transform(list: List<MovieEntity>) : List<MovieItemsModel>{
            val items = arrayListOf<MovieItemsModel>()
            list.forEach {
                items.add(transform(it))
            }
            return items
        }
    }
}