package com.example.shoeprotector.model.log

@kotlinx.serialization.Serializable
data class CardLogItem(
    val id: String,
    val name: String?,
    val isBlocked: Boolean?
)
