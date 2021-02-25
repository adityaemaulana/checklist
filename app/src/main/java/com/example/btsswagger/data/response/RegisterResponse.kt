package com.example.btsswagger.data.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("errorMessage")
    val errorMessage: String?,
)
