package com.example.foodtestwork.data.model

data class CategoriesResponse(
    val categories : MutableList<Categories> = mutableListOf()
)

data class Categories(
    var idCategory : String? = null,
    var strCategory : String? = null,
    var strCategoryThumb : String? = null,
    var strCategoryDescription : String? = null
)