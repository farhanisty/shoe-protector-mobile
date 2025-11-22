package com.example.shoeprotector.ui.components

import android.graphics.drawable.Icon
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)