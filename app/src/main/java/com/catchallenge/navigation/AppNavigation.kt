package com.catchallenge.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.catchallenge.ui.screens.breeddetail.BreedDetailScreen
import com.catchallenge.ui.screens.breedlist.BreedListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "breeds"
    ) {
        breeds(
            route = "breeds",
            onBreedClick = {
                navController.navigate(route = "breeds/$it")
            }
        )

        breedDetail(
            route = "breeds/{Id}",
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

fun NavGraphBuilder.breedDetail(
    route: String,
) = composable(
    route = route
) { navBackStackEntry ->
    val breedId = navBackStackEntry.arguments?.getString("Id")

    requireNotNull(breedId) { "breed id required" }

    BreedDetailScreen(breedId)
}