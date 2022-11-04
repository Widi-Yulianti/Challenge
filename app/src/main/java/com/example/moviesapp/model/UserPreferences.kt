package com.example.moviesapp.model

data class UserPreferences(
    val id: String,
    val username: String,
    val password: String,
    val confirm: String,
    val address: String
)