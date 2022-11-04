package com.example.moviesapp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ResponseUserItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("confirm")
    val confirm: String,
    @SerializedName("address")
    val address: String
) : Serializable