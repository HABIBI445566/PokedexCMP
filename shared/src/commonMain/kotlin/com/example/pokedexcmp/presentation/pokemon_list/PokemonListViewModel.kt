package com.example.pokedexcmp.presentation.pokemon_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcmp.core.navigation.AppRoutes
import com.example.pokedexcmp.core.navigation.Navigator
import com.example.pokedexcmp.domain.usecase.GetPokemonListUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonListUseCase: GetPokemonListUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state

    private val _effect = Channel<PokemonListEffect>()
    val effect = _effect.receiveAsFlow()

    init {
        processIntent(PokemonListIntent.LoadPokemons)
    }

    fun processIntent(intent: PokemonListIntent) {
        when (intent) {
            is PokemonListIntent.LoadPokemons -> fetchPokemons()
            is PokemonListIntent.OnPokemonClicked -> {
                navigator.navigate(AppRoutes.pokemonDetail(intent.name))
            }
        }
    }

    private fun fetchPokemons() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            getPokemonListUseCase()
                .onSuccess { list ->
                    _state.value = _state.value.copy(isLoading = false, pokemonList = list)
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(isLoading = false, error = error.message)
                    _effect.send(PokemonListEffect.ShowError(error.message ?: "Something went wrong"))
                }
        }
    }
}