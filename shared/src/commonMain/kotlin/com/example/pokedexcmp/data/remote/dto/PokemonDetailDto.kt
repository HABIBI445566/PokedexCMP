package com.example.pokedexcmp.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val sprites: SpritesDto,
    val types: List<TypeSlotDto>,
    val stats: List<StatSlotDto>
)

@Serializable
data class SpritesDto(
    val front_default: String?
)

@Serializable
data class TypeSlotDto(
    val type: TypeDto
)

@Serializable
data class TypeDto(
    val name: String
)

@Serializable
data class StatSlotDto(
    val base_stat: Int,
    val stat: StatDto
)

@Serializable
data class StatDto(
    val name: String
)