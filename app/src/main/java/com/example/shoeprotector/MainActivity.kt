package com.example.shoeprotector

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.shoeprotector.ui.HomeScreen
import com.example.shoeprotector.ui.LoginScreen
import com.example.shoeprotector.ui.theme.ShoeProtectorTheme
import androidx.navigation.compose.composable
import kotlinx.coroutines.launch
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shoeprotector.ui.CardHomeScreen
import com.example.shoeprotector.ui.DrawerContent
import com.example.shoeprotector.ui.RegisterCardScreen
import com.example.shoeprotector.ui.SplashScreen
import com.example.shoeprotector.ui.components.NavigationDrawerItem

class MainActivity : ComponentActivity() {
    private lateinit var navController: NavHostController

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoeProtectorTheme{
                navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()

                ModalNavigationDrawer(
                    drawerContent = {
                        DrawerContent(navController, drawerState, listOf(
                            NavigationDrawerItem(
                                "Home",
                                Icons.Default.Home,
                                Screen.HomeScreen.route
                            ),
                            NavigationDrawerItem(
                                "Card",
                                Icons.Default.Favorite,
                                Screen.CardHomeScreen.route
                            )
                        ))
                    },
                    drawerState = drawerState
                ) {

                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Shoe Protector",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Blue
                                ) },
                                navigationIcon = {
                                    IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                        Icon(Icons.Default.Menu, contentDescription = null)
                                    }
                                }
                            )
                        },
                        modifier = Modifier.fillMaxSize()
                    ) { innerPadding ->
                        NavHost(
                            navController = navController,
                            startDestination = Screen.SplashScreen.route
                        ) {
                            composable(
                                route = Screen.SplashScreen.route
                            ) {
                                SplashScreen(
                                    onSuccess = {
                                        navController.navigate(Screen.HomeScreen.route) {
                                            popUpTo(Screen.HomeScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }

                            composable(
                                route = "start"
                            ) {
                                StartScreen(navController)
                            }

                            composable(
                                route = Screen.HomeScreen.route
                            ) {
                                HomeScreen(innerPadding)
                            }

                            composable(
                                route = Screen.CardHomeScreen.route
                            ) {
                                CardHomeScreen(
                                    onAddRegister = {
                                        navController.navigate(Screen.RegisterCardScreen.route)
                                    },
                                    innerPadding
                                )
                            }

                            composable(
                                route = Screen.RegisterCardScreen.route
                            ) {
                                RegisterCardScreen(
                                    innerPadding,
                                    backToCardScreen = {
                                        navController.navigate(Screen.CardHomeScreen.route) {
                                            popUpTo(Screen.CardHomeScreen.route) {
                                                inclusive = true
                                            }
                                        }
                                    }
                                )
                            }

                            composable(
                                route = Screen.LoginScreen.route
                            ) {
                                LoginScreen(innerPadding, onLogin = {
                                    navController.navigate(Screen.HomeScreen.route) {
                                        popUpTo(Screen.HomeScreen.route) {
                                            inclusive = true
                                        }
                                    }
                                })
                            }
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(
    val route: String
) {
    object SplashScreen: Screen("splash_screen")
    object HomeScreen: Screen("home_screen")
    object LoginScreen: Screen("login_screen")
    object CardHomeScreen: Screen("card_home_screen")
    object RegisterCardScreen: Screen("register_card_screen")
}

@Composable
fun StartScreen(navController: NavController, modifier: Modifier = Modifier) {
    val isLoggedIn = false

    LaunchedEffect(Unit) {
        if(isLoggedIn) {
            navController.navigate(Screen.HomeScreen.route) {
                popUpTo("start") { inclusive = true }
            }
        } else {
            navController.navigate(Screen.LoginScreen.route) {
                popUpTo("start") { inclusive = true }
            }
        }
    }
}