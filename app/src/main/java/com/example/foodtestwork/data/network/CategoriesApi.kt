package com.example.foodtestwork.data.network

import com.example.foodtestwork.data.model.CategoriesResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

const val BASE_URL = "https://themealdb.com/"
interface CategoriesApi {
    @GET("/api/json/v1/1/categories.php")
    suspend fun getCategories(): CategoriesResponse
}

val categoriesRetrofit = Retrofit
    .Builder()
    .client(
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }).build()
    )
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(CategoriesApi::class.java)!!