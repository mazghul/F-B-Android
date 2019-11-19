package com.mazghul.influx.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.mazghul.influx.Adapters.Holders.FoodDetailsHolderDTO
import com.mazghul.influx.Adapters.Holders.FoodDetailsViewHolder
import com.mazghul.influx.R

class FoodDetailsAdapter(
    private val foods: ArrayList<FoodDetailsHolderDTO>,
    private val size : Int
) : RecyclerView.Adapter<FoodDetailsViewHolder>() {

    override fun onBindViewHolder(holder: FoodDetailsViewHolder, position: Int) {
        val food = foods[position]
        holder.configure(food.foods)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodDetailsViewHolder {
        val inflatedView = parent.inflate(R.layout.food_card_view, false)
        return FoodDetailsViewHolder(inflatedView)
    }

    override fun getItemCount(): Int {
        return size
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

}