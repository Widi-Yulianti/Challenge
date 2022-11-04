package com.example.moviesapp.network

import com.example.moviesapp.model.DataUser
import com.example.moviesapp.model.ResponseUserItem
import retrofit2.Call
import retrofit2.http.*

interface RestfulUser {

    @GET("users/")
    fun getAllUser(): Call<List<ResponseUserItem>>

    @POST("users")
    fun postUser(@Body request: DataUser): Call<ResponseUserItem>

    @PUT("users/{id}")
    fun updateUser(@Path("id") id : String, @Body request: DataUser ): Call<ResponseUserItem>

    @GET("users/{id}")
    fun getUserById(@Path("id") id : String) : Call<ResponseUserItem>
}