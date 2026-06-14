package com.example.pokedexcmp.presentation.pokemon_list

sealed class PokemonListEffect {
    data class ShowError(val message: String) : PokemonListEffect()
}