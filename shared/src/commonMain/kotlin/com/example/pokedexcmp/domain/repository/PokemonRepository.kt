package com.example.pokedexcmp.domain.repository

import com.example.pokedexcmp.domain.model.Pokemon
import com.example.pokedexcmp.domain.model.PokemonDetail


interface PokemonRepository {
    suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>>
    suspend fun getPokemonDetail(name: String): Result<PokemonDetail>
}