package com.catchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catchallenge.ui.screens.BreedListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost( //
        navController = navController,
        startDestination = "breeds"
    ) {
        breeds(
            route = "breeds",
            onBreedClick = {
                //TODO: navigate
            }
        )
    }
}

fun NavGraphBuilder.breeds(
    route: String,
    onBreedClick: (String) -> Unit,
) = composable(
    route = route
) {
    BreedListScreen(
        onBreedClick = onBreedClick
    )
}
