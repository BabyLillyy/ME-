package com.example.me.data.repository

import com.example.me.data.model.User
import com.example.me.data.repository.dataSource.UsersCachedDataSource
import com.example.me.data.repository.dataSource.UsersLocalDataSource
import com.example.me.data.repository.dataSource.UsersRemoteDataSource
import com.example.me.domain.repository.UsersRepository

class UsersRepositoryImpl(
	private val usersCachedDataSource: UsersCachedDataSource,
	private val usersLocalDataSource: UsersLocalDataSource,
	private val usersRemoteDataSource: UsersRemoteDataSource
): UsersRepository {
	override suspend fun getUsers(): User? = usersRemoteDataSource.getUsers().body()

	override suspend fun updateUsers(user: User) {
		val mUsers = usersRemoteDataSource.saveUsers(user).body()
		usersLocalDataSource.saveUsers(mUsers)
		usersCachedDataSource.saveUser(mUsers)
	}
}