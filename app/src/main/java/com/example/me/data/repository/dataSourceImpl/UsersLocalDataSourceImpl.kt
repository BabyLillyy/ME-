package com.example.me.data.repository.dataSourceImpl

import com.example.me.data.database.dao.UsersDao
import com.example.me.data.model.User
import com.example.me.data.repository.dataSource.UsersLocalDataSource

class UsersLocalDataSourceImpl(private val usersDao: UsersDao): UsersLocalDataSource {
	override suspend fun getUsers(): User = usersDao.getUsers()

	override suspend fun saveUsers(user: User?) {
		usersDao.clearUsers()
		user?.let { usersDao.saveUsers(it) }
	}
}