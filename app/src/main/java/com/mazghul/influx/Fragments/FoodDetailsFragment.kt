package com.mazghul.influx.Fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazghul.influx.Adapters.FoodDetailsAdapter
import com.mazghul.influx.Adapters.Holders.FoodDetailsHolderDTO
import com.mazghul.influx.Models.FBResponse

import com.mazghul.influx.R

/**
 * A simple [Fragment] subclass.
 */
class FoodDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_food_details, container, false)
        val foodDetailsList: RecyclerView = root.findViewById(R.id.foodDetailsList)

        foodDetailsList.isNestedScrollingEnabled = false
        foodDetailsList.adapter = null
        val linearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        foodDetailsList.layoutManager = linearLayoutManager
        val sectionNumber = arguments?.getInt(ARG_SECTION_NUMBER) ?: 1
        val foodResponse = arguments?.getParcelable<FBResponse>("foodResponse")
        var foodReadings = ArrayList<FoodDetailsHolderDTO>()
        var fandbList = foodResponse!!.FoodList[sectionNumber - 1].fnblist
        for( i in fandbList ) {
            foodReadings.add(
                FoodDetailsHolderDTO(
                    i
                )
            )
        }
        foodDetailsList.adapter = FoodDetailsAdapter(foodReadings, fandbList.size)
        foodDetailsList.layoutManager = linearLayoutManager
        (foodDetailsList.adapter as FoodDetailsAdapter).notifyDataSetChanged()



        return root
    }


    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int, foodResponse: FBResponse): FoodDetailsFragment {
            return FoodDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                    putParcelable("foodResponse", foodResponse)
                }
            }
        }
    }


}
