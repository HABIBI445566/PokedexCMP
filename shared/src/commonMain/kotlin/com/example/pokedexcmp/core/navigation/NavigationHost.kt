package com.example.pokedexcmp.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedexcmp.presentation.pokemon_detail.PokemonDetailScreen
import com.example.pokedexcmp.presentation.pokemon_list.PokemonListScreen
import androidx.savedstate.read
@Composable
fun NavigationHost(navigationDispatcher: NavigationDispatcher) {
    val navController = rememberNavController()

    LaunchedEffect(navController) {
        navigationDispatcher.commands.collect { command ->
            when (command) {
                is NavigationCommand.Navigate ->
                    navController.navigate(command.route)
                is NavigationCommand.NavigateUp ->
                    navController.navigateUp()
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.POKEMON_LIST
    ) {
        composable(AppRoutes.POKEMON_LIST) {
            PokemonListScreen()
        }
        composable(
            route = "${AppRoutes.POKEMON_DETAIL}/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.read { getStringOrNull("name") } ?: return@composable
            PokemonDetailScreen(pokemonName = name)
        }
    }
}