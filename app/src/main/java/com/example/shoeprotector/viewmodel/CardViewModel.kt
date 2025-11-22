package com.example.shoeprotector.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.model.card.CardItem
import com.example.shoeprotector.repository.CardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class FetchCardState {
    data object Loading: FetchCardState()
    data class Success(val cards: List<CardItem>): FetchCardState()
    data class Error(val message: String): FetchCardState()
}

class CardViewModel(
    private val cardRepository: CardRepository
): ViewModel() {
    private val _state = MutableStateFlow<FetchCardState>(FetchCardState.Loading)
    val state = _state.asStateFlow()

    fun initView() {
        viewModelScope.launch {
            try {
                val data = cardRepository.getCards().data
                _state.value = FetchCardState.Success(data)
            } catch(e: Exception) {
                _state.value = FetchCardState.Error(e.message ?: "Error")
            }
        }
    }
}