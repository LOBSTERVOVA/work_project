package com.example.foodtestwork.data.network

import com.example.foodtestwork.data.model.MealModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface SearchFoodApi {
    @GET("/api/json/v1/1/search.php?s")
    suspend fun getMeal(): MealModel
}

val mealRetrofit = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(SearchFoodApi::class.java)!!