package com.snapp.snappbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hafthashtad.navigation.NavigationItem
import com.example.hafthashtad.screen.home.HomeScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavigationItem.Home.route,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        homeScreen(this)
    }

}

fun homeScreen(
    builder: NavGraphBuilder,
) {
    builder.apply {
        composable(
            route = NavigationItem.Home.route,
        ) {
            HomeScreen(onNavigateToDetail = {})

        }
    }
}
