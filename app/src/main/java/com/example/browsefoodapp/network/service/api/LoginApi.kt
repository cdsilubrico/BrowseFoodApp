package com.example.browsefoodapp.network.service.api

import com.example.browsefoodapp.model.useraccount.User
import retrofit2.Call
import retrofit2.http.POST

interface LoginApi {

    @POST("login")
    fun login(): Call<User>

}