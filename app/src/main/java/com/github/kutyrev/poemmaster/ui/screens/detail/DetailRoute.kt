package com.github.kutyrev.poemmaster.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailRoute(
    poemId: Long,
    viewModel: DetailViewModel = koinViewModel<DetailViewModel>()
) {

    LaunchedEffect(Unit) {
        viewModel.loadPoem(poemId)
    }

    DetailScreen(
        viewModel.state.poem,
        viewModel.state.isEditMode,
        viewModel.state.hidePercent,
        viewModel::handleEvent
    )
}