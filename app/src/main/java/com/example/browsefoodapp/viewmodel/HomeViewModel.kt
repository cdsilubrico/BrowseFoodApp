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
    private var overPopularMealData = MutableLiveData<List<MealSeaFood>>()
    private var mealCategoryData = MutableLiveData<List<Category>>()
    private var mealLookUpData = MutableLiveData<MealFullDetails>()

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
            .enqueue(object : Callback<MealSeaFoodList?> {
                override fun onResponse(
                    call: Call<MealSeaFoodList?>,
                    response: Response<MealSeaFoodList?>
                ) {
                    overPopularMealData.value = response.body()!!.meals
                }

                override fun onFailure(call: Call<MealSeaFoodList?>, t: Throwable) {
                    Log.d("GetOverPopularMeal", t.message.toString())
                }
            })
    }

    fun observableOverPopularMealData(): LiveData<List<MealSeaFood>> {
        return overPopularMealData
    }
    //Over Popular Meal

    //Meal Category
    fun getMealCategoryData() {
        RetrofitHttp.theMealDbApi.getAllCategories().enqueue(object : Callback<CategoriesList?> {
            override fun onResponse(
                call: Call<CategoriesList?>,
                response: Response<CategoriesList?>
            ) {
                mealCategoryData.value = response.body()!!.categories
            }

            override fun onFailure(call: Call<CategoriesList?>, t: Throwable) {
                Log.d("GetCategoryMeal", t.message.toString())
            }
        })
    }

    fun observableMealCategoryData(): LiveData<List<Category>> {
        return mealCategoryData
    }
    //Meal Category

    //MealLookUp
    fun getMealData(id: String) {
        RetrofitHttp.theMealDbApi.getMeal(id).enqueue(object : Callback<MealFullDetailsList?> {
            override fun onResponse(
                call: Call<MealFullDetailsList?>,
                response: Response<MealFullDetailsList?>
            ) {
                mealLookUpData.value = response.body()!!.meals[0]
            }

            override fun onFailure(call: Call<MealFullDetailsList?>, t: Throwable) {
                Log.d("GetMealData", t.message.toString())
            }
        })
    }

    fun observableMealData(): LiveData<MealFullDetails> {
        return mealLookUpData
    }
    //MealLookUp

}