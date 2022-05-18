package com.example.browsefoodapp.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.example.browsefoodapp.model.theMealDb.MealByCategorySeaFood

class OverPopularMealDiffUtil(
    private val oldList: List<MealByCategorySeaFood>,
    private val newList: List<MealByCategorySeaFood>
    ) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idMeal == newList[newItemPosition].idMeal
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].idMeal != newList[newItemPosition].idMeal -> {
                false
            }
            oldList[oldItemPosition].strMeal != newList[newItemPosition].strMeal -> {
                false
            }
            oldList[oldItemPosition].strMealThumb != newList[newItemPosition].strMealThumb -> {
                false
            }
            else -> true
        }
    }
}