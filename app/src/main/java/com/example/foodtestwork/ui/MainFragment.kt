package com.example.foodtestwork.ui

import android.content.Context
import android.graphics.drawable.GradientDrawable.Orientation
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodtestwork.data.repository.Repository
import com.example.foodtestwork.databinding.FragmentMainBinding
import com.example.foodtestwork.domain.GetCategoriesUseCase
import com.example.foodtestwork.domain.GetMealUseCase
import com.example.foodtestwork.domain.Mapper
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var pagerAdapter: PagerAdapter
    private var mealAdapter: MealAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        val repository = Repository(requireActivity().getSharedPreferences("foodpref", Context.MODE_PRIVATE))
        val viewModel by viewModels<MainViewModel>{
            object: ViewModelProvider.Factory{
                override fun <T: ViewModel> create(modelClass: Class <T>):T {
                    return MainViewModel(Mapper(), GetCategoriesUseCase(repository), GetMealUseCase(repository)) as? T?: throw IllegalStateException()
                }
            }
        }
        pagerAdapter = PagerAdapter(this)
        binding.pager.adapter = pagerAdapter
        binding.lineCats.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.MealRecycler.layoutManager = LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.meal.collect{
                Log.d("Got meal", it.toString())
                if(it!=null){
                    mealAdapter = MealAdapter(it)
                    binding.MealRecycler.adapter = mealAdapter
                } else {
                    viewModel.mealInit(null)
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.categories.collect{
                Log.d("Got categories:", it.toString())
                if(it!=null){
                    binding.lineCats.adapter = CategoriesAdapter(it, ::filterMeal)
                } else {
                    viewModel.categoriesInit()

                }
            }
        }

        return binding.root
    }

    private fun filterMeal(category: String?){
        if(mealAdapter==null) Toast.makeText(requireContext(), "try again later", Toast.LENGTH_LONG).show() else{
            mealAdapter!!.filter(category)
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}