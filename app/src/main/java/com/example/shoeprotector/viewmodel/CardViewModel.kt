package com.example.shoeprotector.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoeprotector.model.card.CardItem
import com.example.shoeprotector.repository.CardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class FetchCardState {
    data object Loading: FetchCardState()
    data class Success(val cards: List<CardItem>): FetchCardState()
    data class Error(val message: String): FetchCardState()
}

data class CardState (
    var showDeleteDialog: Boolean = false,
    var cardId: String? = null,
    var fetchCardState: FetchCardState = FetchCardState.Loading,
)

class CardViewModel(
    private val cardRepository: CardRepository
): ViewModel() {
    private val _state = MutableStateFlow(CardState())
    val state = _state.asStateFlow()

    fun initView() {
        viewModelScope.launch {
            try {
                val data = cardRepository.getCards().data
                _state.update {
                    it.copy(fetchCardState = FetchCardState.Success(data))
                }
                // Log.d("Fetch Card", _state.value.fetchCardState.javaClass.name)
            } catch(e: Exception) {
                _state.update {
                    it.copy(fetchCardState = FetchCardState.Error(e.message ?: "Error"))
                }
            }
        }
    }

    fun deleteCard() {
        viewModelScope.launch {
            try {
                val result = state.value.cardId?.let { cardRepository.deleteCard(it) }
                closeDeleteDialog()
                initView()
            } catch (e: Exception) {
                closeDeleteDialog()
            }
        }
    }

    fun showDeleteDialog(cardId: String) {
        _state.update {
            it.copy(cardId = cardId, showDeleteDialog = true)
        }
    }

    fun closeDeleteDialog() {
        _state.update {
            it.copy(cardId = null, showDeleteDialog = false)
        }
    }
}