package com.example.shoeprotector.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.model.log.LogItem
import com.example.shoeprotector.repository.LogRepository
import com.google.gson.Gson
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logRepository: LogRepository,
    private val socket: Socket
): ViewModel() {
    private val _logs = MutableStateFlow<List<LogItem>>(emptyList())
    val logs = _logs.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val data = logRepository.getLogs().data

                Log.d("Total Logs", data.size.toString())

                _logs.value = data
            } catch(e: Exception) {
                Log.d("Total Logs", e.message.toString())
            }
        }

        socket.on(Socket.EVENT_CONNECT) {
            Log.d("SOCKET", "connected")
        }

        socket.on("scan_response") { args ->
            val msg = args[0] as String
            Log.d("scan response", msg)
            val data = Gson().fromJson(msg, LogItem::class.java)
            _logs.value = listOf(data) + _logs.value
        }
    }

    override fun onCleared() {
        super.onCleared()
        socket.off("scan_response")
        socket.off(Socket.EVENT_CONNECT)
    }
}