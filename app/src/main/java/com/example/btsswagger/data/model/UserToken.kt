package com.example.btsswagger.data.model

import com.google.gson.annotations.SerializedName

data class UserToken(
    @field:SerializedName("token")
    val token: String
)
