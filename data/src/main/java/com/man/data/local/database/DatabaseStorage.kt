package com.man.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.man.data.local.database.dao.MoviesDao
import com.man.data.local.entity.movie.MovieEntity

@Database(entities = [
    MovieEntity::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseStorage : RoomDatabase() {

    abstract fun movieDao() : MoviesDao

}