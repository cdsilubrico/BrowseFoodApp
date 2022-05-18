package com.example.browsefoodapp.adapter.util

import androidx.recyclerview.widget.DiffUtil
import com.example.browsefoodapp.model.theMealDb.Category

class MealCategoriesDiffUtil(
    private val oldList: List<Category>,
    private val newList: List<Category>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].idCategory == newList[newItemPosition].idCategory
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return when {
            oldList[oldItemPosition].idCategory != newList[newItemPosition].idCategory -> {
                false
            }
            oldList[oldItemPosition].strCategory != newList[newItemPosition].strCategory -> {
                false
            }
            oldList[oldItemPosition].strCategoryThumb != newList[newItemPosition].strCategoryThumb -> {
                false
            }
            oldList[oldItemPosition].strCategoryDescription != newList[newItemPosition].strCategoryDescription -> {
                false
            }
            else -> true
        }
    }
}