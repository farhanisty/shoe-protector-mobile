package com.example.shoeprotector.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.model.log.LogItem
import com.example.shoeprotector.repository.LogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val logRepository: LogRepository
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
    }
}