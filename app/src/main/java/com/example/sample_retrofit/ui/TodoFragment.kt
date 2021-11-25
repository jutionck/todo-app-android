package com.example.sample_retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sample_retrofit.R
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.databinding.FragmentTodoBinding
import com.example.sample_retrofit.utils.SessionManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private lateinit var apiClient: ApiClient
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(requireContext())
        if (!sessionManager.isLoggedIn()) {
            findNavController().navigate(R.id.action_todoFragment_to_loginFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        apiClient = ApiClient()
        binding.apply {
            btnAddTodo.setOnClickListener {
                findNavController().navigate(R.id.action_todoFragment_to_todoFormFragment)
            }

            btnLogout.setOnClickListener {
                Log.i("TODOS", "btnLogout is pressed")
                sessionManager.clearPref()
                findNavController().navigate(R.id.action_todoFragment_to_loginFragment)
            }
        }
        getNameFromLogin()
        loadDataTodo()
    }

    private fun loadDataTodo() {
        apiClient.getApiService(requireContext()).getTodos()
            .enqueue(object : Callback<List<TodoResponse>> {
                override fun onResponse(
                    call: Call<List<TodoResponse>>,
                    response: Response<List<TodoResponse>>
                ) {
                    val responses = response.body()
                    if (responses != null) {
                        val gson = Gson()
                        val todos = gson.fromJson(gson.toJson(responses), Array<TodoModel>::class.java).toList()
                        Log.i("TODOS", "loadDataTodo: $todos")
                    }
                }

                override fun onFailure(call: Call<List<TodoResponse>>, t: Throwable) {
                    Log.i("TODOS", "Login failure ${t.localizedMessage}")
                }
            })
    }

    private fun getNameFromLogin() {
        binding.tvWelcomeName.text =  sessionManager.fetchAuthUsername().toString()
    }
}