package com.example.shoeprotector.di

import com.example.shoeprotector.api.CardApi
import com.example.shoeprotector.api.KtorWebSocketClient
import com.example.shoeprotector.api.LogApi
import com.example.shoeprotector.api.StatusApi
import io.socket.client.IO
import io.socket.client.Socket
import org.koin.dsl.module

val apiModule = module {
    single {
        CardApi(get())
    }

    single { StatusApi(get())}

    single { LogApi(get()) }

    single { KtorWebSocketClient() }

    single<Socket> {
        val opts = IO.Options().apply {
            reconnection = true
            reconnectionAttempts = 30
            reconnectionDelay = 1000
        }

        IO.socket("http://192.168.43.103:3000", opts).apply {
            connect()
        }
    }
}