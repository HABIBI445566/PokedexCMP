package com.example.pokedexcmp
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
/**
 * provideHttpClientEngine
 *
 * @author Habib
 * @date 18-08-2026
 * @version 1.0.0
 */
actual fun provideHttpClientEngine(): HttpClientEngine {

    return OkHttp.create()

}