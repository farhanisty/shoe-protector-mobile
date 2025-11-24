package com.example.shoeprotector.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText

class KtorWebSocketClient {

    private val client = HttpClient(OkHttp) {
        install(WebSockets)
    }

    private var session: WebSocketSession? = null

    suspend fun connect() {
        session = client.webSocketSession {
            url("ws://192.168.43.103:3000/ws")
        }
    }

    suspend fun listen(onMessage: (String) -> Unit) {
        val ws = session ?: return
        try {
            for (frame in ws.incoming) {
                if (frame is Frame.Text) {
                    onMessage(frame.readText())
                }
            }
        } catch (_: Exception) {
        }
    }

    suspend fun close() {
        session?.close()
        client.close()
    }
}
