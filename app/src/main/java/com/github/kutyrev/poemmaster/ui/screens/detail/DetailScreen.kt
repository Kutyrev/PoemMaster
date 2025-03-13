package com.github.kutyrev.poemmaster.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.kutyrev.poemmaster.R
import com.github.kutyrev.poemmaster.model.Poem
import com.github.kutyrev.poemmaster.model.PoemWordVisualization
import com.github.kutyrev.poemmaster.ui.screens.detail.model.DetailEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    poem: Poem,
    poemWords: List<PoemWordVisualization>,
    isEditMode: Boolean,
    hidePercent: Int,
    onEvent: (DetailEvent) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(poem.name)
                },
                navigationIcon = {
                    IconButton(onClick = { TODO() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_to_list)
                        )
                    }
                },
                actions = {
                    OutlinedButton(onClick = { onEvent(DetailEvent.changeHidePercentage) }) {
                        Text(text = hidePercent.toString().plus(" %"))
                    }
                    Text(text = stringResource(R.string.edit_mode))
                    Switch(
                        checked = isEditMode,
                        onCheckedChange = { onEvent(DetailEvent.changeIsEditMode) })
                }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            Text(poem.text)
            /*Text(
                buildAnnotatedString {
                    withLink {
                        LinkAnnotation.Clickable
                    }
                }
            )*/
        }
    }
}