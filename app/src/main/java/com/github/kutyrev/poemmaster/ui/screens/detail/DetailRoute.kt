package com.github.kutyrev.poemmaster.ui.screens.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailRoute(
    poemId: Long,
    goBack: () -> Unit,
    viewModel: DetailViewModel = koinViewModel<DetailViewModel>()
) {
    LaunchedEffect(Unit) {
        viewModel.loadPoem(poemId)
    }

    DetailScreen(
        poemName = viewModel.state.poemName,
        poemWords = viewModel.state.poemWords,
        poemText = viewModel.state.poemText,
        isEditMode = viewModel.state.isEditMode,
        hidePercent = viewModel.state.hidePercent,
        numberOpened = viewModel.state.numberOfOpenedWords,
        onEvent = viewModel::handleEvent,
        goBack = goBack
    )
}