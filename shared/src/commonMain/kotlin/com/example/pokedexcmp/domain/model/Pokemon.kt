package com.example.pokedexcmp.domain.model

data class Pokemon(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val types: List<String>
)

data class PokemonDetail(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val height: Int,
    val weight: Int,
    val types: List<String>,
    val stats: List<PokemonStat>
)

data class PokemonStat(
    val name: String,
    val value: Int
)