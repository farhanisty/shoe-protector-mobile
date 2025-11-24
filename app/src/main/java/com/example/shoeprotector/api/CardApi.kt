package com.example.shoeprotector.api

import com.example.shoeprotector.model.card.CardResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.Serializable

@Serializable
data class RegisterCardRequest(
    val id: String,
    val name: String
)

class CardApi(private val client: HttpClient) {
    private val baseUrl = "http://192.168.43.103:3000/api"

    suspend fun getAllCards(): CardResponse {
        return client.get("$baseUrl/card").body()
    }

    suspend fun deleteCardById(cardId: String): Boolean {
        val status = client.delete("$baseUrl/card/$cardId").status.value;

        return status in 200..299
    }

    suspend fun createCard(cardId: String, name: String) {
        val status = client.post("$baseUrl/card") {
            contentType(ContentType.Application.Json)

            setBody(RegisterCardRequest(cardId, name))
        }.status.value
    }
}