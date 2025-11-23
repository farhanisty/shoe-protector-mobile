package com.example.shoeprotector.repository

import com.example.shoeprotector.model.card.CardResponse

interface CardRepository {
    suspend fun getCards(): CardResponse
    suspend fun deleteCard(cardId: String): Boolean
}