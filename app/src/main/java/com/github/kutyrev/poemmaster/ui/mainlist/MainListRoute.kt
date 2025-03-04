package com.github.kutyrev.poemmaster.ui.mainlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.kutyrev.poemmaster.ui.mainlist.model.MainListEffect
import com.github.kutyrev.poemmaster.ui.mainlist.model.MainListViewModel
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

    MainListScreen(viewModel.state, viewModel::handleEvent)
}