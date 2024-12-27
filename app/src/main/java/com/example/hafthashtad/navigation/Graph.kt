package com.snapp.snappbox.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hafthashtad.navigation.ID
import com.example.hafthashtad.navigation.NavigationItem
import com.example.hafthashtad.screen.detail.CatDetailScreen
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
        catDetailScreen(this)
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

fun catDetailScreen(
    builder: NavGraphBuilder,

    ) {
    builder.apply {
        composable(
            route = "${NavigationItem.Detail.route}?$ID={$ID}",
            arguments = listOf(navArgument(name = ID) {
                type = NavType.StringType
            })
        ) {
            CatDetailScreen(onNavigateToParent = { })

        }
    }
}
