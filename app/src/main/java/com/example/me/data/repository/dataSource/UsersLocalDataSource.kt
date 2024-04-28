package com.example.me.data.repository.dataSource

import com.example.me.data.model.User

interface UsersLocalDataSource {
	suspend fun getUsers(): User
	suspend fun  saveUsers(user: User?)
}