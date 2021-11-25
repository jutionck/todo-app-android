package com.example.sample_retrofit.data.api

import com.example.sample_retrofit.data.api.response.LoginResponse
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.model.LoginModel
import com.example.sample_retrofit.utils.Constants
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginModel): Call<LoginResponse>

    @GET(Constants.TODOS_URL)
    fun getTodos(): Call<TodoResponse>

    // other api service
}