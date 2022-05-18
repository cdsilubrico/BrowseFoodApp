package com.example.browsefoodapp.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.browsefoodapp.databinding.RvMealCategoriesTemplateBinding
import com.example.browsefoodapp.model.theMealDb.Category

class MealCategoriesAdapter : RecyclerView.Adapter<MealCategoriesAdapter.HomeFragmentViewHolder>() {

    private var mealCategoriesList = ArrayList<Category>()

    inner class HomeFragmentViewHolder(val binding: RvMealCategoriesTemplateBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setMealCategories(mealCategoriesList: ArrayList<Category>) {
        this.mealCategoriesList = mealCategoriesList
        //notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RvMealCategoriesTemplateBinding.inflate(
            inflater,
            parent,
            false
        )

        return HomeFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(mealCategoriesList[position].strCategoryThumb)
            .into(holder.binding.ivMealCategory)

        holder.binding.tvCategoryName.text = mealCategoriesList[position].strCategory
    }

    override fun getItemCount() = mealCategoriesList.size
}