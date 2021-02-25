package com.example.btsswagger.data.model

import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @field: SerializedName("email")
    val email: String,

    @field: SerializedName("password")
    val password: String,

    @field: SerializedName("username")
    val username: String,
)
