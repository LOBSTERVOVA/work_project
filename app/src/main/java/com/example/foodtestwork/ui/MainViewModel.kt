package com.example.foodtestwork.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodtestwork.data.model.CategoriesResponse
import com.example.foodtestwork.data.model.MealModel
import com.example.foodtestwork.domain.GetCategoriesUseCase
import com.example.foodtestwork.domain.GetMealUseCase
import com.example.foodtestwork.domain.Mapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mapper: Mapper, private val getCategoriesUseCase: GetCategoriesUseCase, private val getMealUseCase: GetMealUseCase) : ViewModel() {
    private val _categories = MutableStateFlow<CategoriesResponse?>(null)
    val categories: Flow<CategoriesResponse?> = _categories
    fun categoriesInit(){
        viewModelScope.launch {
            _categories.value = getCategoriesUseCase.invoke()
        }
    }

    private val _meal = MutableStateFlow<MealModel?>(null)
    val meal:Flow<MealModel?> = _meal
    fun mealInit(category:String?){
        viewModelScope.launch {
            val unfilteredMeal = getMealUseCase.invoke()
            if(category==null){
                _meal.value = unfilteredMeal
            } else {
                if(unfilteredMeal==null){
                    _meal.value=null
                }else{
                    _meal.value = mapper.map(unfilteredMeal, category)
                }
            }
        }
    }

    init {
        categoriesInit()
        mealInit(null)
    }

}