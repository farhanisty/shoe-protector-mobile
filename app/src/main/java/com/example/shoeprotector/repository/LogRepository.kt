package com.example.shoeprotector.repository

import com.example.shoeprotector.model.log.LogResponse

interface LogRepository {
    suspend fun getLogs(): LogResponse
}