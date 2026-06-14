package com.example.pokedexcmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListDto(
    val count: Int,
    val next: String?,
    val results: List<PokemonEntryDto>
)

@Serializable
data class PokemonEntryDto(
    val name: String,
    val url: String
)
