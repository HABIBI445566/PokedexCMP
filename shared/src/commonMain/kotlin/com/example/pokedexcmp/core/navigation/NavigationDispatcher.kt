package com.example.pokedexcmp.core.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class NavigationDispatcher : Navigator {

    private val _commands = Channel<NavigationCommand>(Channel.BUFFERED)
    val commands = _commands.receiveAsFlow()

    override fun navigate(route: String) {
        _commands.trySend(NavigationCommand.Navigate(route))
    }

    override fun navigateUp() {
        _commands.trySend(NavigationCommand.NavigateUp)
    }
}