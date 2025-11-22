package com.example.shoeprotector.model.card

@kotlinx.serialization.Serializable
data class CardItem(
    val id: String,
    val name: String,
    val createdAt: String,
    val updatedAt: String
)
