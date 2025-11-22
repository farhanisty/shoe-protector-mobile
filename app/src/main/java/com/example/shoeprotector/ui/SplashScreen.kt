package com.example.shoeprotector.ui

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.shoeprotector.viewmodel.ConnectionState
import com.example.shoeprotector.viewmodel.SplashViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SplashScreen(
    onSuccess: () -> Unit = {},
    onFailed: () -> Unit = {},
    viewModel: SplashViewModel = koinViewModel()
) {
    val state = viewModel.connectionState

    LaunchedEffect(Unit) {
        viewModel.checkServer()
    }

    LaunchedEffect(state) {
        when(state) {
            ConnectionState.Success -> onSuccess()
            ConnectionState.Failed -> onFailed()
            else -> {}
        }
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text("Splash Screen")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun SplashScreenPreview() {
    SplashScreen()
}