package com.example.moviesapp.model

import java.io.Serializable

data class DataUser(
    val username: String,
    val password: String,
    val confirm: String,
    val address: String
) : Serializable