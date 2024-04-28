package com.example.me.domain.usecase

import com.example.me.data.model.User
import com.example.me.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class GetUsersUseCase(private val usersRepository: UsersRepository) {
	suspend fun getUsers(): User? = withContext(IO) {
		return@withContext usersRepository.getUsers()
	}
}