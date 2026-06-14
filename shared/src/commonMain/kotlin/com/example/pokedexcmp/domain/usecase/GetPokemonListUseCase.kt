package com.example.pokedexcmp.domain.usecase

import com.example.pokedexcmp.domain.model.Pokemon
import com.example.pokedexcmp.domain.repository.PokemonRepository

class GetPokemonListUseCase (
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(limit: Int = 20, offset: Int = 0): Result<List<Pokemon>> {
        return repository.getPokemonList(limit, offset)
    }
}