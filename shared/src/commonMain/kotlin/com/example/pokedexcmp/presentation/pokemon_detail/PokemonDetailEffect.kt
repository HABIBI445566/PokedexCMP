package com.example.pokedexcmp.presentation.pokemon_detail

sealed class PokemonDetailEffect {
    data class ShowError(val message: String) : PokemonDetailEffect()
}