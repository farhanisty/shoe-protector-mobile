package com.example.shoeprotector.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel: ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
    }
}