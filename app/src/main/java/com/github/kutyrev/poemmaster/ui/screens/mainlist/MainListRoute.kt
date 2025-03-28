package com.github.kutyrev.poemmaster.ui.screens.mainlist

import androidx.compose.runtime.Composable
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainListRoute(
    goToPoemScreen: (Long) -> Unit,
    viewModel: MainListViewModel = koinViewModel<MainListViewModel>()
) {
    MainListScreen(
        viewModel.state.poemsList,
        viewModel.effect,
        viewModel::handleEvent,
        goToPoemScreen
    )
}