package com.github.kutyrev.poemmaster.ui.mainlist

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.github.kutyrev.poemmaster.ui.mainlist.model.MainListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainListRoute(
    navController: NavHostController,
    viewModel: MainListViewModel = koinViewModel<MainListViewModel>()
) {
    MainListScreen(viewModel.state, viewModel::handleEvent)
}