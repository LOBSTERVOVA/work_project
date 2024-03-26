package com.example.foodtestwork.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodtestwork.data.model.MealModel
import com.example.foodtestwork.databinding.MealRecyclerLayoutBinding

class MealAdapter(private val initialMealModel: MealModel) : RecyclerView.Adapter<MealViewHolder>() {
    var mutableMealModel = initialMealModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val binding = MealRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return MealViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mutableMealModel.meals.size
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        val meal = mutableMealModel.meals[position]
        Log.d("GLIDE res", meal.strMealThumb.toString())
        with(holder.binding){
            Glide.with(mealImage)
                .load(mutableMealModel.meals[position].strMealThumb)
                .fitCenter()
                .into(mealImage)
            mealTitle.text = meal.strMeal
            val ingrs = listOf(
                meal.strIngredient1, meal.strIngredient2, meal.strIngredient3, meal.strIngredient4, meal.strIngredient5, meal.strIngredient6, meal.strIngredient7, meal.strIngredient8, meal.strIngredient9, meal.strIngredient10, meal.strIngredient11, meal.strIngredient12, meal.strIngredient13, meal.strIngredient14, meal.strIngredient15, meal.strIngredient16, meal.strIngredient17, meal.strIngredient18, meal.strIngredient19, meal.strIngredient20
            )
            val combinedString = ingrs
                .filter { !it.isNullOrEmpty() }
                .joinToString(separator = ", ")
            description.text = combinedString
        }
    }

    fun filter(category: String?){
        if(category==null){
            mutableMealModel = initialMealModel
            notifyDataSetChanged()
        } else {
            val meals = initialMealModel.meals.filter {
                it.strCategory == category
            }
            mutableMealModel = MealModel(meals.toMutableList())
            notifyDataSetChanged()
        }
    }
}

class MealViewHolder(val binding: MealRecyclerLayoutBinding) : RecyclerView.ViewHolder(binding.root)