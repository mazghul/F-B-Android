package com.mazghul.influx.Adapters.Holders

import android.content.Context
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.mazghul.influx.Models.Fnblist
import com.mazghul.influx.Models.Food
import com.mazghul.influx.R
import kotlinx.android.synthetic.main.food_card_view.view.*

data class FoodDetailsHolderDTO(
    val foods: Fnblist
)

class FoodDetailsViewHolder(v: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(v) {
    private var view: View = v

    private var systolicDiastolicValueTextView: TextView =
        view.findViewById(R.id.foodName)

    fun configure(food: Fnblist) {
        view.foodName.text = food.Name
        Glide.with(view.context).load(food.ImageUrl).into(view.food_image)

    }
}