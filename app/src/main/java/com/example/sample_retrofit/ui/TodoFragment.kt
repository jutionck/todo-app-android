package com.example.sample_retrofit.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sample_retrofit.R
import com.example.sample_retrofit.data.adapter.TodoAdapter
import com.example.sample_retrofit.data.api.request.TodoRequestUpdate
import com.example.sample_retrofit.data.api.response.TodoResponse
import com.example.sample_retrofit.data.config.ApiClient
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.data.viewmodel.TodoViewModel
import com.example.sample_retrofit.databinding.FragmentTodoBinding
import com.example.sample_retrofit.utils.SessionManager
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding
    private lateinit var sessionManager: SessionManager
    private lateinit var todoViewModel: TodoViewModel
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(requireContext())
        if (!sessionManager.isLoggedIn()) {
            findNavController().navigate(R.id.action_todoFragment_to_loginFragment)
        }
        todoViewModel = ViewModelProvider(requireActivity())[TodoViewModel::class.java]
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
        todoViewModel.getAllTodo()
        subscribe()
        getNameFromLogin()
    }

    private fun subscribe() {
        todoViewModel.todoListLiveData.observe(viewLifecycleOwner, {
            todoAdapter = TodoAdapter(it, todoViewModel)
            binding.apply {
                todoRecyclerView.apply {
                    layoutManager = LinearLayoutManager(requireContext())
                    adapter = todoAdapter
                }
            }
        })
    }

    private fun getNameFromLogin() {
        binding.tvWelcomeName.text =  sessionManager.fetchAuthUsername().toString()
    }
}