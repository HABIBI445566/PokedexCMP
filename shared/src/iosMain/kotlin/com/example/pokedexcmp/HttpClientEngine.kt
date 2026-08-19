package com.example.pokedexcmp
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
/**
 * HttpClientEngine
 *
 * @author Habib
 * @date 18-08-2026
 * @version 1.0.0
 */
actual fun provideHttpClientEngine(): HttpClientEngine {
    return Darwin.create()
}