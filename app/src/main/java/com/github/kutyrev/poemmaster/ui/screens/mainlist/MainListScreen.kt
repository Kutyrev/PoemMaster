package com.github.kutyrev.poemmaster.ui.screens.mainlist

import androidx.compose.foundation.clickable
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
import com.github.kutyrev.poemmaster.ui.screens.mainlist.model.MainListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.ui.res.dimensionResource

@Composable
fun MainListScreen(state: MainListState, onEvent: (MainListEvent) -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = { onEvent(MainListEvent.AddNewPoem) }) {
            Icon(Icons.Filled.Add, stringResource(R.string.list_fab_content_desc))
        }
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(state.poemsList) { poem ->
                ElevatedCard(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_std))
                        .fillMaxWidth()
                        .clickable(onClick = { onEvent(MainListEvent.GoToPoem(poem.id)) })
                ) {
                    Text(poem.name)
                }
            }
        }
    }
}