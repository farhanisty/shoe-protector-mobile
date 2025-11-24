package com.example.shoeprotector.repository

import com.example.shoeprotector.api.CardApi
import com.example.shoeprotector.model.card.CardResponse

class CardRepositoryImpl(
    private val cardApi: CardApi
): CardRepository {
    override suspend fun getCards(): CardResponse = cardApi.getAllCards()
    override suspend fun deleteCard(cardId: String) = cardApi.deleteCardById(cardId)
    override suspend fun createCard(cardId: String, name: String) {
        cardApi.createCard(cardId, name)
    }
}