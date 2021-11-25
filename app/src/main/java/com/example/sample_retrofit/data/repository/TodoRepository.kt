package com.example.sample_retrofit.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sample_retrofit.data.api.ApiService
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.model.TodoModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoRepository(
    private val apiService: ApiService
) {
    // GET ALL
    private var _todoListLiveData = MutableLiveData<List<TodoModel>>()
    val todoListLiveData: LiveData<List<TodoModel>> get() = _todoListLiveData

    // GET BY ID
    private var _todoLiveData = MutableLiveData<TodoModel>()
    val todoLiveData: LiveData<TodoModel> get() = _todoLiveData

    private lateinit var context: Context

    fun getAllTodo() {
        apiService.getTodos()
            .enqueue(object : Callback<List<TodoResponse>> {
                override fun onResponse(
                    call: Call<List<TodoResponse>>,
                    response: Response<List<TodoResponse>>
                ) {
                    val responses = response.body()
                    if (responses != null) {
                        val gson = Gson()
                        _todoListLiveData.value = gson.fromJson(gson.toJson(responses), Array<TodoModel>::class.java).toList()
                    }
                }

                override fun onFailure(call: Call<List<TodoResponse>>, t: Throwable) {
                    Log.i("TODOS", "Get todos failure ${t.localizedMessage}")
                }
            })
    }
}