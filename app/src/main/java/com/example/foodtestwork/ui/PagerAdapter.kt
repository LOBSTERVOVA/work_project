package com.example.foodtestwork.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(fragment:Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        val frag = PagerFragment()
        frag.arguments = Bundle().apply {
            putInt(POSITION_INT, position)
        }
        return frag
    }

}