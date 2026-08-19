package com.example.pokedexcmp.data.api

import io.ktor.client.engine.HttpClientEngine

/**
 * HttpClientEngineProvider
 *
 * @author Habib
 * @date 18-08-2026
 * @version 1.0.0
 */
expect fun provideHttpClientEngine(): HttpClientEngine