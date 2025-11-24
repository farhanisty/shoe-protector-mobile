package com.example.shoeprotector.repository

import com.example.shoeprotector.api.KtorWebSocketClient

class WebSocketRepository(
    private val client: KtorWebSocketClient
) {
    suspend fun startListening(onCard: (String) -> Unit) {
        client.connect()
        client.listen { message ->
            onCard(message)
        }
    }

    suspend fun stop() {
        client.close()
    }
}