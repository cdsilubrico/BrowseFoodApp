package com.example.browsefoodapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.browsefoodapp.model.theMealDb.*
import com.example.browsefoodapp.network.service.retrofit.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var randomMealLiveData = MutableLiveData<Meal>()
    private var overPopularMealData = MutableLiveData<List<MealByCategorySeaFood>>()
    private var mealCategoryData = MutableLiveData<List<Category>>()

    //Random Meal
    fun getRandomMeal() {
        RetrofitHttp.theMealDbApi.getRandomMeal().enqueue(object : Callback<MealList?> {
            override fun onResponse(call: Call<MealList?>, response: Response<MealList?>) {
                randomMealLiveData.value = response.body()!!.meals[0]
            }

            override fun onFailure(call: Call<MealList?>, t: Throwable) {
                Log.d("GetRandomMeal", t.message.toString())
            }
        })
    }

    fun observableRandomMealLiveData(): LiveData<Meal> {
        return randomMealLiveData
    }
    //Random Meal

    //Over Popular Meal
    fun getOverPopularMealData() {
        RetrofitHttp.theMealDbApi.getMealBySeafood("Seafood")
            .enqueue(object : Callback<MealSeaFood?> {
                override fun onResponse(
                    call: Call<MealSeaFood?>,
                    response: Response<MealSeaFood?>
                ) {
                    overPopularMealData.value = response.body()!!.meals
                }

                override fun onFailure(call: Call<MealSeaFood?>, t: Throwable) {
                    Log.d("GetOverPopularMeal", t.message.toString())
                }
            })
    }

    fun observableOverPopularMealData(): LiveData<List<MealByCategorySeaFood>> {
        return overPopularMealData
    }
    //Over Popular Meal

    //Meal Category
    fun getMealCategoryData()
    {
        RetrofitHttp.theMealDbApi.getAllCategories().enqueue(object : Callback<MealCategories?> {
            override fun onResponse(
                call: Call<MealCategories?>,
                response: Response<MealCategories?>
            ) {
                mealCategoryData.value = response.body()!!.categories
            }

            override fun onFailure(call: Call<MealCategories?>, t: Throwable) {
                Log.d("GetCategoryMeal",t.message.toString())
            }
        })
    }

    fun observableMealCategoryData():LiveData<List<Category>>
    {
        return mealCategoryData
    }

}