package com.example.pokedexcmp.presentation.pokemon_detail

sealed class PokemonDetailIntent {
    data class LoadPokemonDetail(val name: String) : PokemonDetailIntent()
    object NavigateBack : PokemonDetailIntent()
}
