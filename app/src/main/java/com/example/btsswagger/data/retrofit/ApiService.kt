package com.example.btsswagger.data.retrofit

import com.example.btsswagger.data.model.LoginModel
import com.example.btsswagger.data.response.LoginResponse
import com.example.btsswagger.data.model.RegisterModel
import com.example.btsswagger.data.response.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    @Headers("Content-Type: application/json")
    fun doLogin(@Body body: LoginModel): Call<LoginResponse>

    @POST("register")
    @Headers("Content-Type: application/json")
    fun doRegister(@Body body: RegisterModel): Call<RegisterResponse>
}