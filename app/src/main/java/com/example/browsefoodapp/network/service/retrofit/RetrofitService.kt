package com.example.browsefoodapp.network.service.retrofit
import com.example.browsefoodapp.network.service.api.LoginApi
import com.example.browsefoodapp.network.service.api.TheMealDbApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private const val BASE_URL_FOOD_API = "https://www.themealdb.com/api/json/v1/1/"
    val theMealDbApi: TheMealDbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_FOOD_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TheMealDbApi::class.java)
    }

    private const val BASE_URL_LOGIN_API = "http://localhost:8080/api/"
    val accountApi: LoginApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_LOGIN_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LoginApi::class.java)
    }

}