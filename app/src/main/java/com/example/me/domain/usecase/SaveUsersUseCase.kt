package com.example.me.domain.usecase

import com.example.me.data.model.User
import com.example.me.domain.repository.UsersRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

class SaveUsersUseCase(private val usersRepository: UsersRepository) {
	suspend fun saveUser(users: User) = withContext(IO) {
		return@withContext usersRepository.updateUsers(users)
	}
}