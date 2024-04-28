package com.example.me.data.repository.dataSourceImpl

import com.example.me.data.api.ApiService
import com.example.me.data.model.User
import com.example.me.data.repository.dataSource.UsersRemoteDataSource
import retrofit2.Response

class UserRemoteDataSourceImpl(private val apiService: ApiService): UsersRemoteDataSource {
	override suspend fun getUsers(): Response<User> = apiService.getUsers()
	override suspend fun saveUsers(users: User): Response<User> {
		TODO("Not yet implemented")
	}
}