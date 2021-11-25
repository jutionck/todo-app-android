package com.example.sample_retrofit.data.api.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TodoRequest(
    @SerializedName("name")
    var todoName: String,
    var isDone: Boolean = false,
    var photo: String = ""
)

data class TodoRequestUpdate(
    @SerializedName("id")
    @Transient
    var id: Int,
    @SerializedName("name")
    var todoName: String,
    @SerializedName("isDone")
    var isDone: Boolean,
    @SerializedName("photo")
    var photo: String = ""
)


