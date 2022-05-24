package com.example.browsefoodapp.network.service.api

import com.example.browsefoodapp.model.theMealDb.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDbApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("filter.php?")
    fun getMealBySeafood(@Query("c") categoryName: String): Call<MealSeaFoodList>

    @GET("categories.php")
    fun getAllCategories(): Call<CategoriesList>

    @GET("lookup.php?")
    fun getMeal(@Query("i") id:String): Call<MealFullDetailsList>

}