package com.example.pokedexcmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.pokedexcmp.App
import com.example.pokedexcmp.core.di.appModule
import com.example.pokedexcmp.core.di.initKoin
import org.koin.core.context.startKoin

fun main() = application {
    println("Running on JVM: " + System.getProperty("java.home"))
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "PokedexCMP",
    ) {
        App()
    }
}