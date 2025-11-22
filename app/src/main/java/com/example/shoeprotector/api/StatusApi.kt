package com.example.shoeprotector.api

import com.example.shoeprotector.model.StatusResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import java.net.UnknownHostException

class StatusApi(private val client: HttpClient) {
    private val baseUrl: String = "http://192.168.43.103:3000/api"

    suspend fun getStatus(): StatusResponse {
        try {
            val response = client.get("$baseUrl/status")

            if(response.status.value < 200 || response.status.value > 299) {
                throw UnknownHostException("Network Error")
            }
            return response.body()
        } catch (e: Exception) {
            throw UnknownHostException("Network Error")
        }
    }
}