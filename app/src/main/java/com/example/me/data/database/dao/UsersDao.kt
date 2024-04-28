package com.example.me.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.me.data.model.User
import retrofit2.http.Body

@Dao
interface UsersDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun saveUsers(@Body movie: User)

	@Query("DELETE FROM User")
	suspend fun clearUsers()

	@Query("SELECT * FROM User")
	suspend fun getUsers(): User
}