package com.example.pokedexcmp

import androidx.compose.runtime.Composable
import com.example.pokedexcmp.core.navigation.NavigationHost
import com.example.pokedexcmp.core.navigation.NavigationDispatcher
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun App() {
    KoinContext {
        val navigationDispatcher = koinInject<NavigationDispatcher>()
        NavigationHost(navigationDispatcher = navigationDispatcher)
    }
}