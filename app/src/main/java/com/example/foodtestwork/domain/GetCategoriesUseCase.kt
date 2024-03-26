package com.example.foodtestwork.domain

import com.example.foodtestwork.data.model.CategoriesResponse
import com.example.foodtestwork.data.model.MealModel
import com.example.foodtestwork.data.repository.Repository

class GetCategoriesUseCase(private val repository: Repository) {
    suspend fun invoke(): CategoriesResponse? = repository.getCategories()
}