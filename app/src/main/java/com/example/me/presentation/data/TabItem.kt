package com.example.me.presentation.data

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem (
    val title: String,
    val value: String,
    val screen: @Composable () -> Unit
)