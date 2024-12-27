package com.example.hafthashtad.navigation

sealed class NavigationItem(val route: String) {
    data object Home : NavigationItem(Screens.HOME.name)

    data object Detail : NavigationItem(Screens.DETAIL.name)
}