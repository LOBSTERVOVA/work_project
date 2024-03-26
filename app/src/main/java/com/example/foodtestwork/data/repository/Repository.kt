package com.example.foodtestwork.data.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.foodtestwork.data.model.CategoriesResponse
import com.example.foodtestwork.data.model.MealModel
import com.example.foodtestwork.data.network.categoriesRetrofit
import com.example.foodtestwork.data.network.mealRetrofit
import com.google.gson.Gson

class Repository(val prefs: SharedPreferences) {
    suspend fun getCategories(): CategoriesResponse?{
        val categories = prefs.getString("categories", null)
        if(categories != null)return Gson().fromJson(categories, CategoriesResponse::class.java) else {
            return try {
                val cats = categoriesRetrofit.getCategories()
                val editor = prefs.edit()
                editor.apply {
                    putString("categories", Gson().toJson(cats))
                    commit()
                }
                cats
            }catch (e:Exception){
                Log.d("Error occured", e.toString())
                null
            }
        }

    }

    suspend fun getMeal(): MealModel?{
        val meals = prefs.getString("meals", null)
        if(meals!=null)return Gson().fromJson(meals, MealModel::class.java) else {
            return try {
                val result = mealRetrofit.getMeal()
                val editor = prefs.edit()
                editor.apply {
                    putString("meals", Gson().toJson(result))
                    commit()
                }
                result
            }catch (e:Exception){
                Log.d("Error occured", e.toString())
                null
            }
        }

    }

}