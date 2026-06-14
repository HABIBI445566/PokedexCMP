package com.example.pokedexcmp.presentation.pokemon_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonDetailScreen(
    pokemonName: String,
    viewModel: PokemonDetailViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(pokemonName) {
        viewModel.processIntent(PokemonDetailIntent.LoadPokemonDetail(pokemonName))
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PokemonDetailEffect.ShowError -> { }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(pokemonName) },
                navigationIcon = {
                    IconButton(onClick = {
                        viewModel.processIntent(PokemonDetailIntent.NavigateBack)
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.error != null -> {
                    Text(
                        text = state.error ?: "Unknown error",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                state.pokemon != null -> {
                    val pokemon = state.pokemon!!
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = pokemon.imageUrl,
                            contentDescription = pokemon.name,
                            modifier = Modifier.size(180.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "#${pokemon.id} ${pokemon.name.replaceFirstChar { it.uppercase() }}",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            pokemon.types.forEach { type ->
                                SuggestionChip(
                                    onClick = {},
                                    label = { Text(type.replaceFirstChar { it.uppercase() }) }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            StatCard(label = "Height", value = "${pokemon.height / 10.0} m")
                            StatCard(label = "Weight", value = "${pokemon.weight / 10.0} kg")
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Base Stats",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        pokemon.stats.forEach { stat ->
                            StatRow(name = stat.name, value = stat.value)
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StatCard(label: String, value: String) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = value, style = MaterialTheme.typography.titleMedium)
            Text(text = label, style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun StatRow(name: String, value: Int) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = name.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.width(120.dp)
            )
            Text(
                text = value.toString(),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.End
            )
        }
        LinearProgressIndicator(
            progress = { value / 150f },
            modifier = Modifier.fillMaxWidth()
        )
    }
}