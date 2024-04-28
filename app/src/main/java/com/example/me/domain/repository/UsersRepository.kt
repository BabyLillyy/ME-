package com.example.me.domain.repository

import com.example.me.data.model.User

interface UsersRepository {
	suspend fun getUsers(): User?
	suspend fun updateUsers(user: User)
}