package com.example.shoeprotector.api

import com.example.shoeprotector.model.card.CardResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CardApi(private val client: HttpClient) {
    private val baseUrl = "http://192.168.43.103:3000/api"

    suspend fun getAllCards(): CardResponse {
        return client.get("$baseUrl/card").body()
    }
}