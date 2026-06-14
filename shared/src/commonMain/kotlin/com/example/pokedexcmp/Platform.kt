package com.example.pokedexcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform