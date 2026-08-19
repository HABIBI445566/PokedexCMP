package com.example.pokedexcmp.core.di

import com.example.pokedexcmp.data.remote.api.PokeApiService
import com.example.pokedexcmp.data.api.createHttpClient
import com.example.pokedexcmp.data.repository.PokemonRepositoryImpl
import com.example.pokedexcmp.domain.repository.PokemonRepository
import com.example.pokedexcmp.domain.usecase.GetPokemonDetailUseCase
import com.example.pokedexcmp.domain.usecase.GetPokemonListUseCase
import com.example.pokedexcmp.core.navigation.NavigationDispatcher
import com.example.pokedexcmp.core.navigation.Navigator
import com.example.pokedexcmp.presentation.pokemon_detail.PokemonDetailViewModel
import com.example.pokedexcmp.presentation.pokemon_list.PokemonListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { createHttpClient() }
    single { PokeApiService(get()) }
    single<PokemonRepository> { PokemonRepositoryImpl(get()) }
    single { GetPokemonListUseCase(get()) }
    single { GetPokemonDetailUseCase(get()) }
    single { NavigationDispatcher() }
    single<Navigator> { get<NavigationDispatcher>() }
    viewModel { PokemonListViewModel(get(), get()) }
    viewModel { PokemonDetailViewModel(get(), get()) }
}