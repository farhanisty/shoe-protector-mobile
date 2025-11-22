package com.example.shoeprotector.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shoeprotector.viewmodel.RegisterCardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RegisterCardScreen(scaffoldPadding: PaddingValues, modifier: Modifier = Modifier, registerCardViewModel: RegisterCardViewModel = koinViewModel()) {
    val inputName by registerCardViewModel.inputName.collectAsState()
    val idCard by registerCardViewModel.idCard.collectAsState()
    val isLoading by registerCardViewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .padding(scaffoldPadding)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Text("Mode: Register")
            Text(
                "Scan Kartu Anda",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.LightGray
            )

            Spacer(
                modifier= Modifier
                    .height(12.dp)
            )

            val currentIdCard = idCard?.toString() ?: "-"

            Text("ID: $currentIdCard")

            Spacer(
                modifier = Modifier
                    .height(24.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    inputName,
                    onValueChange = { registerCardViewModel.updateInputName(it) },
                    placeholder = {
                        Text("Nama pemilik kartu")
                    },
                    enabled = !isLoading && idCard != null,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                )

                Button(
                    onClick = {
                        registerCardViewModel.performRegister()
                    },
                    enabled = !isLoading && idCard != null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(if (isLoading)"Loading..." else "Register")
                }
            }
        }
    }
}