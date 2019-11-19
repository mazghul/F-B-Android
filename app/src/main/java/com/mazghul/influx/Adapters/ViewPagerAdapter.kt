package com.mazghul.influx.Adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mazghul.influx.Fragments.FoodDetailsFragment
import com.mazghul.influx.Models.FBResponse

class ViewPagerAdapter(fm: FragmentManager, private val tabsCount : Int, private val foodResponse : FBResponse) :
    FragmentPagerAdapter(fm)  {

    override fun getItem(position: Int): Fragment {
        return FoodDetailsFragment.newInstance(position + 1, foodResponse)
    }

    override fun getCount(): Int {
        return tabsCount
    }
}