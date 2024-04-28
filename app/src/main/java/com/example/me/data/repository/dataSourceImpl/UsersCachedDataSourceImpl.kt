package com.example.me.data.repository.dataSourceImpl

import com.example.me.data.model.User
import com.example.me.data.repository.dataSource.UsersCachedDataSource

class UsersCachedDataSourceImpl: UsersCachedDataSource {
	private lateinit var users:User
	override suspend fun getUser(): User = users
	override suspend fun saveUser(userList: User?) {
		if (userList != null) {
			users = userList
		}
	}
}