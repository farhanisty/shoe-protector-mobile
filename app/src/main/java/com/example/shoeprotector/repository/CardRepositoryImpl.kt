package com.example.shoeprotector.repository

import com.example.shoeprotector.api.CardApi
import com.example.shoeprotector.model.card.CardResponse

class CardRepositoryImpl(
    private val cardApi: CardApi
): CardRepository {
    override suspend fun getCards(): CardResponse = cardApi.getAllCards()
}