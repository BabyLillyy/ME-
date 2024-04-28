package com.example.me.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class User(
	@PrimaryKey
	@SerializedName("id")
	val id:String,
	@SerializedName("username")
	val username: String,
	@SerializedName("email")
	val email: String,
	@SerializedName("pass")
	val pass: String,
)