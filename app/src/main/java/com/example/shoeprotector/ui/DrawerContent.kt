package com.example.shoeprotector.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shoeprotector.Screen
import com.example.shoeprotector.ui.components.NavigationDrawerItem
import kotlinx.coroutines.launch

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    navItems: List<NavigationDrawerItem>
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(16.dp))

        val scope = rememberCoroutineScope()
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            navItems.forEach { navItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(bottom = 12.dp)
                        .fillMaxWidth()
                ) {
                    Spacer(modifier = Modifier.width(12.dp))

                    Icon(navItem.icon, contentDescription = null)

                    Text(
                        text = navItem.label,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(navItem.route)
                                scope.launch { drawerState.close() }
                            }
                            .padding(12.dp)
                    )
                }

            }
        }
    }
}