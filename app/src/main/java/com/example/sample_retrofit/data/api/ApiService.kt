package com.example.sample_retrofit.data.api

import com.example.sample_retrofit.data.api.request.TodoRequest
import com.example.sample_retrofit.data.api.request.TodoRequestUpdate
import com.example.sample_retrofit.data.api.response.LoginResponse
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.model.LoginModel
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.utils.Constants
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginModel): Call<LoginResponse>

    @GET(Constants.TODOS_URL)
    fun getTodos(): Call<List<TodoResponse>>

    @POST(Constants.TODOS_URL)
    fun saveTodo(@Body request: TodoRequest): Call<TodoResponse>

    @GET("${Constants.TODOS_URL}/{id}")
    fun getTodoById(@Path("id") id: Int) : Call<TodoResponse>

    @PUT(Constants.TODOS_URL)
    fun updateTodo(@Body request: TodoRequestUpdate): Call<TodoResponse>

    @DELETE("${Constants.TODOS_URL}/{id}")
    fun deleteTodo(@Path("id") id: Int): Call<Unit>

}