package com.example.sample_retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.sample_retrofit.R
import com.example.sample_retrofit.data.api.request.TodoRequest
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.data.viewmodel.TodoViewModel
import com.example.sample_retrofit.databinding.FragmentTodoFormBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoFormFragment : Fragment() {

    private lateinit var binding: FragmentTodoFormBinding
    private lateinit var apiClient: ApiClient
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiClient = ApiClient()
        todoViewModel = ViewModelProvider(requireActivity())[TodoViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoFormBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnBackTodo.setOnClickListener {
                findNavController().navigate(R.id.action_todoFormFragment_to_todoFragment)
            }

            btnSaveTodo.setOnClickListener {
                saveTodo()
                findNavController().navigate(R.id.action_todoFormFragment_to_todoFragment)
            }
        }
    }

    private fun saveTodo() {
        binding.apply {
            val newTodo = TodoRequest(teTodoName.text.toString())
            todoViewModel.saveTodo(newTodo)
        }
    }
}