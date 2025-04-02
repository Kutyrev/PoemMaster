package com.github.kutyrev.poemmaster.ui.screens.mainlist

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.kutyrev.poemmaster.R
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListEvent
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.github.kutyrev.poemmaster.model.PoemHeader
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun MainListScreen(
    poemsList: List<PoemHeader>,
    effectFlow: Flow<MainListEffect>,
    onEvent: (MainListEvent) -> Unit,
    goToPoemScreen: (Long) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        effectFlow.collect {
            when (it) {
                is MainListEffect.ToPoem -> goToPoemScreen(it.poemId)
                is MainListEffect.ShowSwipeToDeleteConfirmSnackbar -> {
                    coroutineScope.launch {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = context.getString(
                                R.string.snackbar_deleting,
                                it.poemHeader.name
                            ),
                            actionLabel = context.getString(R.string.snackbar_undo_button),
                            duration = SnackbarDuration.Long
                        )
                        when (snackbarResult) {
                            SnackbarResult.Dismissed -> {
                                onEvent(MainListEvent.DeleteSnackbarDismissed(it.poemHeader))
                            }

                            SnackbarResult.ActionPerformed -> {
                                onEvent(MainListEvent.DeleteSnackbarActionPerformed(it.poemHeader))
                            }
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(MainListEvent.AddNewPoem) }) {
                Icon(Icons.Filled.Add, stringResource(R.string.list_fab_content_desc))
            }
        }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(poemsList) { poem ->
                SwipeBox(
                    onDelete = { onEvent(MainListEvent.DeleteSwipePerformed(poem)) }
                ) {
                    ElevatedCard(
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen.padding_std))
                            .fillMaxWidth()
                            .clickable(onClick = { onEvent(MainListEvent.GoToPoem(poem.id)) }),
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = dimensionResource(R.dimen.elevation_std))
                    ) {
                        Text(
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_std)),
                            text = poem.name,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SwipeBox(
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    content: @Composable () -> Unit
) {
    val swipeState = rememberSwipeToDismissBoxState()

    lateinit var icon: ImageVector
    lateinit var alignment: Alignment
    val color: Color

    icon = Icons.Outlined.Delete
    alignment = Alignment.CenterEnd

    if (swipeState.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
        icon = Icons.Outlined.Delete
        alignment = Alignment.CenterEnd
        color = MaterialTheme.colorScheme.errorContainer
    } else if (swipeState.dismissDirection == SwipeToDismissBoxValue.Settled) {
        color = MaterialTheme.colorScheme.background
    } else {
        color = MaterialTheme.colorScheme.background
    }

    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = swipeState,
        enableDismissFromStartToEnd = false,
        backgroundContent = {
            Box(
                contentAlignment = alignment,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color)
            ) {
                Icon(
                    modifier = Modifier.minimumInteractiveComponentSize(),
                    imageVector = icon, contentDescription = null
                )
            }
        }
    ) {
        content()
    }

    when (swipeState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            LaunchedEffect(swipeState) {
                onDelete()
                swipeState.reset()
            }
        }

        SwipeToDismissBoxValue.StartToEnd -> {
            LaunchedEffect(swipeState) {
                swipeState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }

        SwipeToDismissBoxValue.Settled -> {
        }
    }
}
