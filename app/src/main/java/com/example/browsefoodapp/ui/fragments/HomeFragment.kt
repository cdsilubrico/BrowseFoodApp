package com.example.browsefoodapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.browsefoodapp.adapter.home.MealCategoriesAdapter
import com.example.browsefoodapp.adapter.home.OverPopularMealAdapter
import com.example.browsefoodapp.databinding.FragmentHomeBinding
import com.example.browsefoodapp.model.theMealDb.Category
import com.example.browsefoodapp.model.theMealDb.MealByCategorySeaFood
import com.example.browsefoodapp.ui.activities.MealDetailsActivity
import com.example.browsefoodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeFragmentOverPopularAdapter: OverPopularMealAdapter
    private lateinit var homeFragmentCategoriesAdapter: MealCategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentOverPopularAdapter = OverPopularMealAdapter()
        homeFragmentCategoriesAdapter = MealCategoriesAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //HomeViewAdapter

        //prepare View Model
        prepareHomeViewModel()

        //prepare Data
        prepareData()

        //update UI
        updateRandomMeal()
        onRandomMealClick()
        prepareOverPopularItemsRecView()
        observeOverPopularMeal()
        prepareMealCategoryItemsRecView()
        observeMealCategory()
    }

    private fun prepareHomeViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun updateRandomMeal() {
        homeViewModel.observableRandomMealLiveData().observe(
            viewLifecycleOwner
        ) { t ->
            Glide.with(this@HomeFragment)
                .load(t!!.strMealThumb)
                .into(binding.ivRandomMeal)
            binding.tvMealName.text = t.strMeal
        }
    }

    private fun onRandomMealClick() {
        homeViewModel.observableRandomMealLiveData().observe(viewLifecycleOwner) { t ->
            binding.cvRandomMeal.setOnClickListener {
                val intent = Intent(activity, MealDetailsActivity::class.java)
                intent.putExtra("MEAL_NAME", t.strMeal)
                intent.putExtra("MEAL_AREA", t.strArea)
                intent.putExtra("MEAL_CATEGORY", t.strCategory)
                intent.putExtra("MEAL_THUMB", t.strMealThumb)
                intent.putExtra("MEAL_INSTRUCTIONS", t.strInstructions)
                intent.putExtra("YOUTUBE_LINK", t.strYoutube)
                startActivity(intent)
            }
        }
    }

    private fun prepareOverPopularItemsRecView() {
        binding.rvOverPopularMeals.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            adapter = homeFragmentOverPopularAdapter
        }
    }

    private fun observeOverPopularMeal() {
        homeViewModel.observableOverPopularMealData()
            .observe(viewLifecycleOwner) { mealByCategory ->
                homeFragmentOverPopularAdapter.setOverPopularMeal(overPopularMealList = mealByCategory as ArrayList<MealByCategorySeaFood>)
            }
    }

    private fun prepareMealCategoryItemsRecView() {
        binding.rvMealCategory.apply {
            layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
            adapter = homeFragmentCategoriesAdapter
        }
    }

    private fun observeMealCategory() {
        homeViewModel.observableMealCategoryData().observe(viewLifecycleOwner)
        { mealCategory ->
            homeFragmentCategoriesAdapter.setMealCategories(mealCategoriesList = mealCategory as ArrayList<Category>)
        }
    }

    private fun prepareData() {
        homeViewModel.getMealCategoryData()
        homeViewModel.getOverPopularMealData()
        homeViewModel.getRandomMeal()
    }
}