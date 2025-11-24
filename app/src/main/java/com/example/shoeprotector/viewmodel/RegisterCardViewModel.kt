package com.example.shoeprotector.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.repository.WebSocketRepository
import io.socket.client.Socket
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterCardViewModel(
    private val socket: Socket
): ViewModel() {
    private val _inputName = MutableStateFlow("")
    val inputName = _inputName.asStateFlow()

    private val _idCard = MutableStateFlow<String?>(null)
    val idCard = _idCard.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        socket.on(Socket.EVENT_CONNECT) {
            Log.d("SOCKET", "connected")
        }

        socket.on("card_scanned") { args ->
            val msg = args[0] as String
            _idCard.value = msg
        }

    }

    fun updateInputName(newInputName: String) {
        _inputName.value = newInputName
    }

    fun performRegister() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                delay(3000L)

                updateInputName("")
                _idCard.value = null
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        socket.off("card_scanned")
        socket.off(Socket.EVENT_CONNECT)
    }
}