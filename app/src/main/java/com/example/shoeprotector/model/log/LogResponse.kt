package com.example.shoeprotector.model.log

@kotlinx.serialization.Serializable
data class LogResponse(
    val total: Int,
    val data: List<LogItem>
)