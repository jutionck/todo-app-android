package com.example.sample_retrofit.data.api.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String
)
