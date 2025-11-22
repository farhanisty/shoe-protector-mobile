package com.example.shoeprotector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RegisterCardViewModel: ViewModel() {
    private val _inputName = MutableStateFlow("")
    val inputName = _inputName.asStateFlow()

    private val _idCard = MutableStateFlow<String?>(null)
    val idCard = _idCard.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading = _isLoading.asStateFlow()

    fun updateInputName(newInputName: String) {
        _inputName.value = newInputName
    }

    fun performRegister() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                delay(3000L)

                updateInputName("")
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }
}