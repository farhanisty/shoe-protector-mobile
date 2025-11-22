package com.example.shoeprotector.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.collectAsState
import com.example.shoeprotector.viewmodel.LoginViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun LoginScreen(paddingValues: PaddingValues, onLogin: () -> Unit, modifier: Modifier = Modifier, loginViewModel: LoginViewModel = koinViewModel()) {
    val email by loginViewModel.email.collectAsState()
    var password by remember { mutableStateOf("") }
    var isVisiblePassword by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        Text(
            "Shoe Protector",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Blue
        )
        Spacer(modifier = modifier.height(24.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                email,
                onValueChange = { loginViewModel.updateEmail(it) },
                label = { Text("Masukkan Email") }
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value= password,
                onValueChange = { password = it },
                label = { Text("Masukkan Password") },
                visualTransformation =
                    if (isVisiblePassword) VisualTransformation.None
                    else PasswordVisualTransformation(),
                trailingIcon = {
                    val icon = if (isVisiblePassword) Icons.Default.Build else Icons.Default.Add
                    IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                        Icon(imageVector=icon, contentDescription=null)
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLogin,
                modifier = modifier
                    .width(200.dp)
                ) {
                Text("Login")
            }
        }
    }
}