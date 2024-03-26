package com.example.foodtestwork.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.foodtestwork.R
import com.example.foodtestwork.databinding.FragmentPagerBinding

const val POSITION_INT = "position"
class PagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPagerBinding.inflate(layoutInflater)
        arguments?.takeIf { it.containsKey(POSITION_INT) }?.apply {
            when(getInt(POSITION_INT)){
                0 -> {
                    binding.imagePager.setImageDrawable(resources.getDrawable(R.drawable.adv1))
                }
                1 -> {
                    binding.imagePager.setImageDrawable(resources.getDrawable(R.drawable.adv2))
                }
                2 -> {
                    binding.imagePager.setImageDrawable(resources.getDrawable(R.drawable.adv3))
                }
            }
        }
        return binding.root
    }

}