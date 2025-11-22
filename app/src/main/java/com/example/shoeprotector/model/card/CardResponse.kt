package com.example.shoeprotector.model.card

@kotlinx.serialization.Serializable
data class CardResponse(
    val total: Int,
    val data: List<CardItem>
)
