package com.example.pokedexcmp.presentation.pokemon_detail

import com.example.pokedexcmp.domain.model.PokemonDetail

data class PokemonDetailState(
    val isLoading: Boolean = false,
    val pokemon: PokemonDetail? = null,
    val error: String? = null
)