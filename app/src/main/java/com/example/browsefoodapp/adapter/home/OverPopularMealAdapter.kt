package com.example.browsefoodapp.adapter.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.browsefoodapp.adapter.util.OverPopularMealDiffUtil
import com.example.browsefoodapp.databinding.RvItemsTemplateBinding
import com.example.browsefoodapp.model.theMealDb.MealSeaFood

class OverPopularMealAdapter :
    RecyclerView.Adapter<OverPopularMealAdapter.HomeFragmentViewHolder>() {

    lateinit var onItemClick: ((MealSeaFood) -> Unit)
    private var overPopularMealList = emptyList<MealSeaFood>()

    /*
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    inner class HomeFragmentViewHolder(val binding: RvItemsTemplateBinding) :
        RecyclerView.ViewHolder(binding.root)

    fun setOverPopularMeal(overPopularMealList: ArrayList<MealSeaFood>) {
        val overPopularMealListDiffUtil =
            OverPopularMealDiffUtil(overPopularMealList, this.overPopularMealList)
        val diffResults = DiffUtil.calculateDiff(overPopularMealListDiffUtil)
        this.overPopularMealList = overPopularMealList
        diffResults.dispatchUpdatesTo(this)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeFragmentViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = RvItemsTemplateBinding.inflate(
            inflater,
            parent,
            false
        )

        return HomeFragmentViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
        updateItemImage(holder, position)

        holder.itemView.setOnClickListener {
            onItemClick.invoke(overPopularMealList[position])
        }

    }

    // Return the size of your dataset (invoked by the layout manager)-
    override fun getItemCount() = overPopularMealList.size

    private fun updateItemImage(holder: HomeFragmentViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(overPopularMealList[position].strMealThumb)
            .into(holder.binding.ivRandomMeal)
    }

}