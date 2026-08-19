package com.example.pokedexcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.pokedexcmp.core.di.initKoin

fun MainViewController() = ComposeUIViewController {
    App()
}

fun doInitKoin() {
    initKoin()
}