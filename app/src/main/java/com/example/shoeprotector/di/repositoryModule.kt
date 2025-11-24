package com.example.shoeprotector.di

import com.example.shoeprotector.repository.CardRepository
import com.example.shoeprotector.repository.CardRepositoryImpl
import com.example.shoeprotector.repository.WebSocketRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CardRepository>{
        CardRepositoryImpl(get())
    }

    single { WebSocketRepository(get()) }
}