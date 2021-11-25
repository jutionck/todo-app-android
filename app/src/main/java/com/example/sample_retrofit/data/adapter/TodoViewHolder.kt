package com.example.sample_retrofit.data.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sample_retrofit.data.model.TodoModel
import com.example.sample_retrofit.databinding.TodoLayoutBinding

class TodoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val binding = TodoLayoutBinding.bind(itemView)

    fun bind(todo: TodoModel) {
        binding.apply {
            tvTodoName.text = todo.name
            checkbox.isChecked = todo.isDone
            btnDeleteTodo.setOnClickListener {
                // TODO DELETE
            }
        }
    }
}