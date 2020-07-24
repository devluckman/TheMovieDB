package com.man.data.di

import com.man.data.remote.ApiGenerator
import com.man.data.remote.service.ApiMovieService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun providesApiMovieService(): ApiMovieService {
        return ApiGenerator().createApi(ApiMovieService::class.java)
    }
}