package com.example.me.presentation.data

import com.example.me.presentation.utils.Screen

data class NavigationItem(
	val route: String = Screen.Dashboard.name,
	val icon: Int = 0,
	val badgeCount: Int = 0
)