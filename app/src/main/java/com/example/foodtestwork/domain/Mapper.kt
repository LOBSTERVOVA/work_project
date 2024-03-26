package com.example.foodtestwork.domain

import com.example.foodtestwork.data.model.MealModel

class Mapper {
    fun map(mealModel: MealModel, category: String):MealModel{
        val meal = mealModel.meals.filter { it.strCategory==category }
        return MealModel(meal.toMutableList())
    }
}