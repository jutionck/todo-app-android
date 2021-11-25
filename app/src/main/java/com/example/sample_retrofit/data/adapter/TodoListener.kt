package com.example.sample_retrofit.data.adapter

import com.example.sample_retrofit.data.model.TodoModel

interface TodoListener {
    fun onDelete(todo: TodoModel)
}