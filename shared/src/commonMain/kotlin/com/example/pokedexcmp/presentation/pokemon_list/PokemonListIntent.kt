package com.example.pokedexcmp.presentation.pokemon_list

sealed class PokemonListIntent {
    object LoadPokemons : PokemonListIntent()
    data class OnPokemonClicked(val name: String) : PokemonListIntent()
}