package com.example.me.data.api


import com.example.me.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
	@GET("user")
	suspend fun getUsers(): Response<User>
}