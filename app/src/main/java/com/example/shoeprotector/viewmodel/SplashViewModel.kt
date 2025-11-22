package com.example.shoeprotector.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.api.StatusApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.UnknownHostException

sealed class ConnectionState {
    data object Loading : ConnectionState()
    data object Success : ConnectionState()
    data object Failed : ConnectionState()
}

class SplashViewModel(
    private val statusApi: StatusApi
): ViewModel() {
    var connectionState by mutableStateOf<ConnectionState>(ConnectionState.Loading)
        private set

    fun checkServer() {
        viewModelScope.launch {
            delay(1500)

            val success = try {
                statusApi.getStatus()

                true
            } catch(e: UnknownHostException) {
                false
            }

            connectionState = if(success) ConnectionState.Success else ConnectionState.Failed
        }
    }
}