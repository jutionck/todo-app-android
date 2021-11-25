package com.example.sample_retrofit.data.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.sample_retrofit.data.adapter.TodoListener
import com.example.sample_retrofit.data.api.request.TodoRequest
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.data.repository.TodoRepository
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel(
    application: Application
) : AndroidViewModel(application), TodoListener {

    private lateinit var todoRepository: TodoRepository

    @SuppressLint("StaticFieldLeak")
    private val context = getApplication<Application>().applicationContext

    // GET ALL
    private var _todoListLiveData = MutableLiveData<List<TodoModel>>()
    val todoListLiveData: LiveData<List<TodoModel>> get() = _todoListLiveData

    // GET BY ID
    private var _todoLiveData = MutableLiveData<TodoModel>()
    val todoLiveData: LiveData<TodoModel> get() = _todoLiveData
    private val apiClient = ApiClient()

    fun getAllTodo() {
        apiClient.getApiService(context).getTodos()
            .enqueue(object : Callback<List<TodoResponse>> {
                override fun onResponse(
                    call: Call<List<TodoResponse>>,
                    response: Response<List<TodoResponse>>
                ) {
                    val responses = response.body()
                    if (responses != null) {
                        val gson = Gson()
                        _todoListLiveData.value =
                            gson.fromJson(gson.toJson(responses), Array<TodoModel>::class.java)
                                .toList()
                        Log.i("TODOS", "getAllTodo(): ${_todoListLiveData.value}")
                    }
                }

                override fun onFailure(call: Call<List<TodoResponse>>, t: Throwable) {
                    Log.i("TODOS", "Get todos failure ${t.localizedMessage}")
                }
            })
    }

    fun saveTodo(todo: TodoRequest) {
        apiClient.getApiService(context).saveTodo(todo)
            .enqueue(object: Callback<TodoResponse> {
                override fun onResponse(
                    call: Call<TodoResponse>,
                    response: Response<TodoResponse>
                ) {
                    Log.i("TODOS", "saveTodo(): ${response.body()}")
                }

                override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                    Log.i("TODOS", "Save todo failure ${t.printStackTrace()}")
                }

            })

    }

    override fun onDelete(todo: TodoModel) {
        Log.i("TODOS", "ID: ${todo.id}")
        apiClient.getApiService(context).deleteTodo(todo.id).enqueue(object : Callback<Unit> {
            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                getAllTodo()
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Log.i("TODOS", "onDelete failure ${t.printStackTrace()}")
            }

        })
    }

    private fun getTodoById(id: Int) {
        apiClient.getApiService(context).getTodoById(id).enqueue(object : Callback<TodoResponse> {
            override fun onResponse(call: Call<TodoResponse>, response: Response<TodoResponse>) {
                val responses = response.body()
                if (responses != null) {
                    val gson = Gson()
                    _todoLiveData.value =
                        gson.fromJson(gson.toJson(responses), TodoModel::class.java)
                    Log.i("TODOS", "getTodoById(): ${_todoLiveData.value}")
                }
            }

            override fun onFailure(call: Call<TodoResponse>, t: Throwable) {
                Log.i("TODOS", "getTodoById failure ${t.printStackTrace()}")

            }

        })
    }
}