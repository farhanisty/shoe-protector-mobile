package com.example.shoeprotector.model.log

@kotlinx.serialization.Serializable
data class LogItem (
    val id: Int,
    val card: CardLogItem,
    val isSuccess: Boolean,
    val createdAt: String
)
