package com.example.pokedexcmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.pokedexcmp.App
import com.example.pokedexcmp.core.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModule)
    }
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokedexCMP",
    ) {
        App()
    }
}