package com.man.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromGenreIdsList(list: List<Int>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toGenreIdsList(genreIdsJson: String): List<Int> {
        val type = object : TypeToken<List<Int>>(){}.type
        return Gson().fromJson(genreIdsJson, type)
    }

    @TypeConverter
    fun fromCountryList(list: List<String>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun toCountryList(countryJson: String): List<String> {
        val type = object : TypeToken<List<String>>(){}.type
        return Gson().fromJson(countryJson, type)
    }

}