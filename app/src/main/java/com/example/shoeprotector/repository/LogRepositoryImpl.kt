package com.example.shoeprotector.repository

import com.example.shoeprotector.api.LogApi
import com.example.shoeprotector.model.log.LogResponse

class LogRepositoryImpl(
    private val logApi: LogApi
) : LogRepository {
    override suspend fun getLogs(): LogResponse = logApi.getAllLogs()
}