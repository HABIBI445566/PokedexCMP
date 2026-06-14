package com.example.pokedexcmp.data.remote.api

import com.example.pokedexcmp.data.remote.dto.PokemonDetailDto
import com.example.pokedexcmp.data.remote.dto.PokemonListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokeApiService(private val client: HttpClient) {

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): PokemonListDto {
        return client.get("pokemon") {
            parameter("limit", limit)
            parameter("offset", offset)
        }.body()
    }

    suspend fun getPokemonDetail(name: String): PokemonDetailDto {
        return client.get("pokemon/$name").body()
    }
}