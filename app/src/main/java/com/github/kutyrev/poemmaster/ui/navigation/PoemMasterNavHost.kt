package com.github.kutyrev.poemmaster.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.kutyrev.poemmaster.ui.screens.detail.DetailRoute
import com.github.kutyrev.poemmaster.ui.screens.mainlist.MainListRoute

@Composable
fun PoemMasterNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: PoemMasterDestinations.Home = PoemMasterDestinations.Home
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<PoemMasterDestinations.Home> {
            MainListRoute(goToPoemScreen = {
                navController.navigate(PoemMasterDestinations.Poem(it))
            })
        }
        composable<PoemMasterDestinations.Poem> { backStackEntry ->
            val poem = backStackEntry.toRoute<PoemMasterDestinations.Poem>()
            DetailRoute(poemId = poem.id, goBack = { navController.navigateUp() })
        }
    }
}