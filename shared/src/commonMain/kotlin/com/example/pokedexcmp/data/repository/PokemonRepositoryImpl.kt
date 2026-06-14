package com.example.pokedexcmp.data.repository

import com.example.pokedexcmp.data.remote.api.PokeApiService
import com.example.pokedexcmp.domain.model.Pokemon
import com.example.pokedexcmp.domain.model.PokemonDetail
import com.example.pokedexcmp.domain.model.PokemonStat
import com.example.pokedexcmp.domain.repository.PokemonRepository

class PokemonRepositoryImpl constructor(
    private val api: PokeApiService
) : PokemonRepository {

    override suspend fun getPokemonList(limit: Int, offset: Int): Result<List<Pokemon>> {
        return try {
            val response = api.getPokemonList(limit, offset)
            val pokemons = response.results.mapIndexed { index, entry ->
                val id = offset + index + 1
                Pokemon(
                    id = id,
                    name = entry.name,
                    imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png",
                    types = emptyList()
                )
            }
            Result.success(pokemons)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getPokemonDetail(name: String): Result<PokemonDetail> {
        return try {
            val response = api.getPokemonDetail(name)
            val detail = PokemonDetail(
                id = response.id,
                name = response.name,
                imageUrl = response.sprites.front_default ?: "",
                height = response.height,
                weight = response.weight,
                types = response.types.map { it.type.name },
                stats = response.stats.map {
                    PokemonStat(name = it.stat.name, value = it.base_stat)
                }
            )
            Result.success(detail)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}