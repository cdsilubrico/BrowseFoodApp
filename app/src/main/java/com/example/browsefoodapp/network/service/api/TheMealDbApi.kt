package com.example.browsefoodapp.network.service.api

import com.example.browsefoodapp.model.MealByCategorySeaFood
import com.example.browsefoodapp.model.MealList
import com.example.browsefoodapp.model.MealSeaFood
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TheMealDbApi {

    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("filter.php?")
    fun getMealBySeafood(@Query("c") categoryName: String): Call<MealSeaFood>


}