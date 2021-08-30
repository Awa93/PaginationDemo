package com.example.demotask.network

import com.example.movieapp.data.MovieDetailResponse
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @POST("/")
    suspend fun getMovieList(@Query("apikey")apiKey:String,
                             @Query("s")s:String,
                             @Query("type")type:String,
                             @Query("page")page:Int):Response<MovieDetailResponse>

    companion object {
        const val BASE_URL:String ="https://www.omdbapi.com"
        var retrofitService: RetrofitService? = null
        private val client = OkHttpClient.Builder().build()
        fun getInstance() : RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}