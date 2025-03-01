package com.github.kutyrev.poemmaster.ui.mainlist

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
import com.github.kutyrev.poemmaster.ui.mainlist.model.MainListEvent
import com.github.kutyrev.poemmaster.ui.mainlist.model.MainListState
import androidx.compose.foundation.lazy.items

@Composable
fun MainListScreen(state: MainListState, onEvent: (MainListEvent) -> Unit) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = TODO()) {
            Icon(Icons.Filled.Add, stringResource(R.string.list_fab_content_desc))
        }
    }) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(state.poemsList) { poem ->
                Text(poem.name)
            }
        }
    }
}