package com.example.pokedexcmp.core.navigation

sealed class NavigationCommand {
    data class Navigate(val route: String) : NavigationCommand()
    data object NavigateUp : NavigationCommand()
}

interface Navigator {
    fun navigate(route: String)
    fun navigateUp()
}

object AppRoutes {
    const val POKEMON_LIST = "pokemon_list"
    const val POKEMON_DETAIL = "pokemon_detail"

    fun pokemonDetail(name: String) = "pokemon_detail/$name"
}