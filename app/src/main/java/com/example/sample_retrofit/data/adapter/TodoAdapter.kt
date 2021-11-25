package com.example.sample_retrofit.data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sample_retrofit.R
import com.example.sample_retrofit.data.model.TodoModel

class TodoAdapter(private val todoList: List<TodoModel>): RecyclerView.Adapter<TodoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.todo_layout, parent, false)
        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    override fun getItemCount() = todoList.size

}