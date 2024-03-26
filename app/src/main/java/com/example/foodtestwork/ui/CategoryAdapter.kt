package com.example.foodtestwork.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.compose.ui.res.colorResource
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtestwork.R
import com.example.foodtestwork.data.model.CategoriesResponse
import com.example.foodtestwork.databinding.CategoryLayoutBinding

class CategoriesAdapter(val categoriesResponse: CategoriesResponse, private val filterFunction: (String?) -> Unit):RecyclerView.Adapter<CategoriesViewHolder>() {
    private var selectedItem:Int? = null
    private var view: CategoryLayoutBinding? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding = CategoryLayoutBinding.inflate(LayoutInflater.from(parent.context))
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return categoriesResponse.categories.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        with(holder.binding){
            textView.text = categoriesResponse.categories[position].strCategory
            cat.setOnClickListener {
                if(selectedItem==position){
                    selectedItem=null
                    view = null
                    textView.setTextColor(Color.parseColor("#A8A8A8"))
                    cat.setCardBackgroundColor(Color.parseColor("#46FFFFFF"))
                    filterFunction(null)
                } else {
                    selectedItem=position
                    if(view!=null){
                        view?.textView?.setTextColor(Color.parseColor("#A8A8A8"))
                        view?.cat?.setCardBackgroundColor(Color.parseColor("#46FFFFFF"))
                    }
                    view = this
                    textView.setTextColor(Color.parseColor("#C60000"))
                    cat.setCardBackgroundColor(Color.parseColor("#46C80808"))
                    filterFunction(textView.text.toString())

                }
                Log.d("item changed:", selectedItem.toString())
            }
        }

    }

}

class CategoriesViewHolder(val binding: CategoryLayoutBinding) : RecyclerView.ViewHolder(binding.root)