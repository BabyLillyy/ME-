package com.example.me.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.me.data.database.dao.UsersDao
import com.example.me.data.model.User

@Database(
	entities = [User::class],
	version = 1,
	exportSchema = false
)
@TypeConverters
abstract class Database: RoomDatabase() {
	abstract fun getUsers(): UsersDao
}