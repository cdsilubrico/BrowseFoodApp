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
import com.example.browsefoodapp.model.theMealDb.MealSeaFood
import com.example.browsefoodapp.ui.activities.MealDetailsActivity
import com.example.browsefoodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeFragmentOverPopularAdapter: OverPopularMealAdapter
    private lateinit var homeFragmentCategoriesAdapter: MealCategoriesAdapter
    var mealID: String = ""

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
        prepareRequiredData()

        //update UI
        updateRandomMeal()
        onRandomMealClick()

        //onRandomMealClick()
        //onMealClick()

        prepareOverPopularItemsRecView()
        observeOverPopularMeal()
        overPopularItemsOnClick()

        prepareMealCategoryItemsRecView()
        observeMealCategory()
    }

    private fun onRandomMealClick() {
        binding.cvRandomMeal.setOnClickListener{
            homeViewModel.getMealData(mealID)
            homeViewModel.observableMealData().observe(viewLifecycleOwner){
                val intent = Intent(activity, MealDetailsActivity::class.java)
                intent.putExtra("MEAL_NAME", it.strMeal)
                intent.putExtra("MEAL_AREA", it.strArea)
                intent.putExtra("MEAL_CATEGORY", it.strCategory)
                intent.putExtra("MEAL_THUMB", it.strMealThumb)
                intent.putExtra("MEAL_INSTRUCTIONS", it.strInstructions)
                intent.putExtra("YOUTUBE_LINK", it.strYoutube)
                startActivity(intent)
            }
        }
    }

    private fun overPopularItemsOnClick() {
        homeFragmentOverPopularAdapter.onItemClick = {

        }
    }

    private fun prepareHomeViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private fun updateRandomMeal() {
        homeViewModel.observableRandomMealLiveData().observe(
            viewLifecycleOwner
        ) { t ->
            mealID = t.idMeal
            Glide.with(this@HomeFragment)
                .load(t.strMealThumb)
                .into(binding.ivRandomMeal)
            binding.tvMealName.text = t.strMeal
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
                homeFragmentOverPopularAdapter.setOverPopularMeal(overPopularMealList = mealByCategory as ArrayList<MealSeaFood>)
            }
    }

    private fun prepareMealCategoryItemsRecView() {
        binding.rvMealCategory.apply {
            layoutManager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
            adapter = homeFragmentCategoriesAdapter
        }
    }

    private fun observeMealCategory() {
        homeViewModel.observableMealCategoryData().observe(viewLifecycleOwner)
        { mealCategory ->
            homeFragmentCategoriesAdapter.setMealCategories(mealCategoriesList = mealCategory as ArrayList<Category>)
        }
    }

    private fun onMealClick() {
        homeViewModel.observableMealData().observe(viewLifecycleOwner) { mealFullDetails ->
            binding.cvRandomMeal.setOnClickListener {
                val intent = Intent(activity, MealDetailsActivity::class.java)
                intent.putExtra("MEAL_NAME", mealFullDetails.strMeal)
                intent.putExtra("MEAL_AREA", mealFullDetails.strArea)
                intent.putExtra("MEAL_CATEGORY", mealFullDetails.strCategory)
                intent.putExtra("MEAL_THUMB", mealFullDetails.strMealThumb)
                intent.putExtra("MEAL_INSTRUCTIONS", mealFullDetails.strInstructions)
                intent.putExtra("YOUTUBE_LINK", mealFullDetails.strYoutube)
                startActivity(intent)
            }
        }
    }

    private fun prepareRequiredData() {
        homeViewModel.getMealCategoryData()
        homeViewModel.getOverPopularMealData()
        homeViewModel.getRandomMeal()
    }
}