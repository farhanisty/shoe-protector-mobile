package com.example.shoeprotector.di

import com.example.shoeprotector.api.CardApi
import com.example.shoeprotector.api.StatusApi
import org.koin.dsl.module

val apiModule = module {
    single {
        CardApi(get())
    }

    single { StatusApi(get())}
}