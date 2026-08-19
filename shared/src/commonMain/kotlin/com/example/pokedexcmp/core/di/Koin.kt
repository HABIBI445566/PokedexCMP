package com.example.pokedexcmp.core.di

/**
 * Koin
 *
 * @author Habib
 * @date 19-08-2026
 * @version 1.0.0
 */
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}