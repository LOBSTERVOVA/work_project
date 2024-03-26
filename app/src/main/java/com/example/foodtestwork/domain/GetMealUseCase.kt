package com.example.foodtestwork.domain

import com.example.foodtestwork.data.model.MealModel
import com.example.foodtestwork.data.repository.Repository

class GetMealUseCase(private val repository: Repository) {
    suspend fun invoke(): MealModel? = repository.getMeal()
}