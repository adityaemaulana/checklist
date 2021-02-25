package com.example.btsswagger.data.response

import com.example.btsswagger.data.model.UserToken
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("errorMessage")
    val errorMessage: String?,

    @field:SerializedName("data")
    val data: UserToken,
)
