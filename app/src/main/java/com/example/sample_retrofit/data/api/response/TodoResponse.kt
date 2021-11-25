package com.example.sample_retrofit.data.api.response

import com.google.gson.annotations.SerializedName

data class TodoResponse(

    @SerializedName("name")
    var todoName: String,
    var isDone: Boolean
)
