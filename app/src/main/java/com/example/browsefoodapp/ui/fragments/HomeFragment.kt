package com.example.browsefoodapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.browsefoodapp.adapter.Home.OverPopularMealAdapter
import com.example.browsefoodapp.databinding.FragmentHomeBinding
import com.example.browsefoodapp.model.theMealDb.MealByCategorySeaFood
import com.example.browsefoodapp.viewmodel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel:HomeViewModel
    private lateinit var homeFragmentOverPopularAdapter: OverPopularMealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeFragmentOverPopularAdapter = OverPopularMealAdapter()
    }

    private fun updateRandomMealImageView()
    {
        homeViewModel.getRandomMeal()
        homeViewModel.observableRandomMealLiveData().observe(viewLifecycleOwner
        ) { t ->
            Glide.with(this@HomeFragment)
                .load(t!!.strMealThumb)
                .into(binding.ivRandomMeal)
            binding.tvMealName.text = t.strMeal
        }
    }

    private fun prepareHomeViewModel() {
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //HomeViewAdapter

        //prepare View Model
        prepareHomeViewModel()

        //update UI
        updateRandomMealImageView()

        prepareOverPopularItemsRecView()
        homeViewModel.getOverPopularMealData()
        observeOverPopularMeal()
    }

    private fun prepareOverPopularItemsRecView()
    {
        binding.rvOverPopularMeals.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
            adapter = homeFragmentOverPopularAdapter
        }
    }

    private fun observeOverPopularMeal()
    {
        homeViewModel.observableOverPopularMealData().observe(viewLifecycleOwner){
            mealByCategory ->
            homeFragmentOverPopularAdapter.setOverPopularMeal(overPopularMealList = mealByCategory as ArrayList<MealByCategorySeaFood>)
        }
    }
}