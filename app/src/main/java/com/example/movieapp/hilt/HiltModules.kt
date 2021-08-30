package com.example.movieapp.hilt

import com.example.demotask.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {

    @Provides
    fun myApi():RetrofitService{
        return Retrofit.Builder().baseUrl("https://www.omdbapi.com").addConverterFactory(
            GsonConverterFactory.create())
            .build().create(RetrofitService::class.java)
    }

}