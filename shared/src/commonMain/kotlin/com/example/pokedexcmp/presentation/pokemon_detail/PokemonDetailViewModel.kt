package com.example.pokedexcmp.presentation.pokemon_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexcmp.core.navigation.Navigator
import com.example.pokedexcmp.domain.usecase.GetPokemonDetailUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _state = MutableStateFlow(PokemonDetailState())
    val state: StateFlow<PokemonDetailState> = _state

    private val _effect = Channel<PokemonDetailEffect>()
    val effect = _effect.receiveAsFlow()

    fun processIntent(intent: PokemonDetailIntent) {
        when (intent) {
            is PokemonDetailIntent.LoadPokemonDetail -> fetchDetail(intent.name)
            is PokemonDetailIntent.NavigateBack -> navigator.navigateUp()
        }
    }

    private fun fetchDetail(name: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, error = null)
            getPokemonDetailUseCase(name)
                .onSuccess { detail ->
                    _state.value = _state.value.copy(isLoading = false, pokemon = detail)
                }
                .onFailure { error ->
                    _state.value = _state.value.copy(isLoading = false)
                    _effect.send(PokemonDetailEffect.ShowError(error.message ?: "Something went wrong"))
                }
        }
    }
}