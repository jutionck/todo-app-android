package com.example.sample_retrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sample_retrofit.R
import com.example.sample_retrofit.databinding.FragmentTodoBinding

class TodoFragment : Fragment() {

    private lateinit var binding: FragmentTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                findNavController().navigate(R.id.action_todoFragment_to_loginFragment)
            }
        }
    }

}