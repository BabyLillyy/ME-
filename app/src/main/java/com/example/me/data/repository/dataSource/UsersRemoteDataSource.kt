package com.example.me.data.repository.dataSource

import com.example.me.data.model.User
import retrofit2.Response

interface UsersRemoteDataSource {
	suspend fun getUsers(): Response<User>
	suspend fun saveUsers(users: User): Response<User>
}