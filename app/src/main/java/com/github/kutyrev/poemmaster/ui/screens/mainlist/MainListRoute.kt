package com.github.kutyrev.poemmaster.ui.screens.mainlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListEffect
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainListRoute(
    goToPoemScreen: (Long) -> Unit,
    viewModel: MainListViewModel = koinViewModel<MainListViewModel>()
) {
    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when(it) {
                is MainListEffect.ToPoem -> goToPoemScreen(it.poemId)
            }
        }
    }

    MainListScreen(viewModel.state.poemsList, viewModel::handleEvent)
}