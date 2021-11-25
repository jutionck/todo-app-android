package com.example.sample_retrofit.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.sample_retrofit.R
import com.example.sample_retrofit.databinding.FragmentTodoFormBinding

class TodoFormFragment : Fragment() {

    private lateinit var binding: FragmentTodoFormBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                findNavController().navigate(R.id.action_todoFormFragment_to_todoFragment)
            }
        }
    }

}