package com.example.me.data.repository.dataSource

import com.example.me.data.model.User

interface UsersCachedDataSource {
	suspend fun getUser(): User?
	suspend fun saveUser(userList: User?)
}