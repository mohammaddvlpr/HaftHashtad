package com.example.hafthashtad.navigation

import androidx.navigation.NavController

class NavActions(private val navController: NavController) {

    fun navigateToCatDetail(id: String) {
        navController.navigate("${NavigationItem.Detail.route}?$ID=$id")
    }

    fun navigatesToHome() {
        navController.popBackStack()
    }

}