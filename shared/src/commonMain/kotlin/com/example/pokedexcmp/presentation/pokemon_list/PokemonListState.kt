package com.example.pokedexcmp.presentation.pokemon_list

import com.example.pokedexcmp.domain.model.Pokemon

data class PokemonListState(
    val isLoading: Boolean = false,
    val pokemonList: List<Pokemon> = emptyList(),
    val error: String? = null
)