package com.example.pokedexcmp.domain.usecase

import com.example.pokedexcmp.domain.model.PokemonDetail
import com.example.pokedexcmp.domain.repository.PokemonRepository

class GetPokemonDetailUseCase (
    private val repository: PokemonRepository
) {
    suspend operator fun invoke(name: String): Result<PokemonDetail> {
        return repository.getPokemonDetail(name)
    }
}